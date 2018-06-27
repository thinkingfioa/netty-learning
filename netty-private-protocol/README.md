# netty-private-protocol 开发
```
@author 鲁伟林
工作量:
1. 《Netty权威指南2》中第12章节，详细讲解了私有协议开发，内容。实际项目中，我们常常需要定制化私有协议。
2. 《Netty权威指南2》书中代码存在较多问题，无法运行。我经过研究，修改了书中代码，并成功运行。
3. 成功运行基础上，加入了Kryo编码，与书本中提供的Marshalling编码进行对比。
4. 实验表明，Kryo编码速度的确高于Marshlling编码速度。能够加快Netty的传输速度。
5. 给出6种编码方式在Netty下的使用，并给出详细的案例

GitHub地址: https://github.com/thinkingfioa/netty-learning/tree/master/netty-private-protocol
本人博客地址: http://blog.csdn.net/thinking_fioa/article/details/78265745
```

## 提醒
- 1. netty4.1以上版本中将channelRead(...)等方法从ChannelHandlerAdapter类中移除。添加到子类: ChannelInboundHandlerAdapter中。


# 1. 私有协议开发
- 1. 业务场景: 先进行Tcp连接，然后拆分发送多个数据包，实现文件传输。
- 2. 不同的业务场景需要定制化不同的私有协议，本例子讲给出一个私有化协议，讲述其利用Netty的Tcp协议进行传输。采用5种不同的编解码来实现通信编码，并进行比较。
- 3. 项目实现的私有协议开发，允许发送超多4K字节的数据。具体多大数据，可通过配置文件配置大小
- 4. Netty实际项目中，请不要试图阻塞I/O线程。

## 1.1 场景描述
私有协议实现场景，客户端登录完成后，发起频道订阅，目前实现3个频道: 新闻、体育和娱乐。服务端根据客户端订阅的频道，将对应的频道文件拆分后，发送给客户端。客户端接收到完整的频道文件后，写入文件。

##### 注:

- 1. netty-private-protocol的开发，关键在于理解如何使用Netty来满足自定义协议。所以关于落文件部分，本项目不再继续开发下去。
- 2. 项目重点关注5种不同的编码方式的效率。所以，本项目将不会过多关注于业务开发。频道的文件将控制在4K左右，保证一次性发出。


### 1.1.1 时序图:

## 1.2 消息结构
私有协议共有三个部分:Header + Body + Tail。不同的消息中Header和Tail字段部分将保持一致，Body部分不同的消息将有不同的实现

|消息头部|消息主体|消息尾| 
|:---:|:---:|:---:|
|Header|Body|Tail|

### 1.1.1 Header
- 1. Header头中最重要的字段是:msgLen，表示整个消息长度，关系到Netty粘包粘包解码中maxFrameLength多个字段配置。
- 2. Header头中定义了多种类型：int/long/String/short/byte/Map/Object。已证明协议编码支持多种类型数据。
- 3. Header属性解释如下:

|属性|类型|描述|
|:---:|:---:|:---:|
|sessionID|long|发送方的sessionId|
|msgType|String|消息类型|
|...|...|...|

##### 代码:
```java
public class Header {

    /**
     * 发送方的sessionId
     */
    private long sessionID;

    /**
     * 消息类型，{@code MessageTypeEnum}
     */
    private String msgType;

    /**
     * 发送方姓名
     */
    private String senderName;

    /**
     * short类型占位符
     */
    private short flag;

    /**
     * Byte类型占位符
     */
    private byte oneByte;

    /**
     * 扩展字段，允许动态添加
     */
    private Map<String, Object> attachment = new HashMap<String, Object>();
    
    // getXXX()/ setXXX()
}
```

### 1.1.2 Body
不同的消息类型，Header和Tail部分的字段都一样，但是不同的消息类型，对应于不同字段。共有以下几种消息Body:

|Body类型|作用|
|:---:|:---:|
|LoginBody|登陆消息|
|LogoutBody|注销消息|
|HeartbeatBody|心跳消息|
|ProtocolSubBody|自定义协议订阅消息|
|ProtocolDataBody|自定义协议数据传输消息|

整个过程时序图如:
TODO:: 提供时序图

### 1.1.3 Tail
- 1. Tail是消息尾部字段描述。只有一个checkSum字段，防止消息字节流串改或消息出现错误。
- 2. checkSum的值只涉及Header+Body部分。
- 3. 自定义私有协议的checkSum只有8个bit

##### checkSum计算代码:
```java
public int calcCheckSum(byte[] bytes){
	byte checkSum = 0;
	for(int i = 0; i<bytes.length; i++) {
		checkSum += bytes[i];
	}

	return 0x00ff & checkSum;
}
```

## 1.2 心跳机制
Client端和Server端在数据传输空闲期间，利用心跳机制来保持回话正常。

### 1.2.1 心跳设计的思路

- 1. Client端和Server端收到心跳消息，必须回复心跳应答消息。
- 2. Client端和Server端都监听写空闲事件(WRITER_IDLE)和读空闲事件(READ_IDLE)。写空闲时，发送心跳给对方。读空闲时判断对方未应答心跳次数，如果超过指定次数，则关闭链路。
- 3. Client端的心跳事件处理Handler类:HeartbeatClientHandler。Server端的心跳事件处理Handler类:HeartbeatServerHandler。
- 4. 心跳请求和心跳应答分别对应于Body类: HeartbeatReqBody.class/HeartbeatRespBody.class

## 1.3 编码器/解码器
协议中使用的通用解码器是: LengthFieldBasedFrameDecoder。该解码器自动处理粘包/粘包问题。关于LengthFieldBasedFrameDecoder中5的参数: maxFrameLength/lengthFieldOffset...解释，在下文1.6部分。

### LengthFieldBasedFrameDecoder的使用

## 1.4 Client端

## 1.5 Server端

## 1.6 LengthFieldBasedFrameDecoder的构造函数中5个参数解释
Netty的LengthFieldBasedFrameDecoder编码器构造函数需要6的参数，解决TCP粘包/粘包问题。网上关于这6个参数的中文解释，太垃圾。英文不好的同学，可参考[博客](https://blog.csdn.net/thinking_fioa/article/details/80573483)，理解并会学会使用。英文文档[地址](http://netty.io/5.0/api/io/netty/handler/codec/LengthFieldBasedFrameDecoder.html)。

# 2. 编解码
配置文件中配置项目的编码工具，切换非常简单。

|编码|codec值|
|:---:|:---:|
|Marshalling|(byte)1|
|Kryo|(byte)2|
|Protobuf|(byte)3|
|thrift|(byte)4|
|Avro|(byte)5|

TOTO：类图

## 2.1 Marshalling 编码

### 2.1.1 pom依赖
下面两个dependency缺一不可，否则会有: java.lang.NullPointerException: null。

```
<!-- marshalling  -->
<dependency>
    <groupId>org.jboss.marshalling</groupId>
    <artifactId>jboss-marshalling</artifactId>
    <version>2.0.0.Final</version>
</dependency>
<dependency>
    <groupId>org.jboss.marshalling</groupId>
    <artifactId>jboss-marshalling-serial</artifactId>
	<version>2.0.0.Final</version>
</dependency>
```

### 2.1.2 Marshalling 编码讲解
 - 1. Marshalling编码对应于代码中的package org.lwl.netty.codec.marshalling;
 - 2. Marshalling主要用于对Object进行编码。对于基础的数据类型:List、Map、Integer等直接使用ByteBuf的writeXXX方法编码

## 2.2 Kryo 编码

### 2.2.1 pom依赖
```
<!-- kryo -->
<dependency>
    <groupId>de.javakaffee</groupId>
    <artifactId>kryo-serializers</artifactId>
    <version>0.38</version>
</dependency>
<dependency>
    <groupId>com.esotericsoftware</groupId>
    <artifactId>reflectasm</artifactId>
    <version>1.11.0</version>
</dependency>
```
### 2.2.2 Kryo注意事项

- 1. writeClassAndObject(...)方法会写入class的信息。设计协议时，如果将长度域放在Header中，那么将会导致Kryo解码时，找不到对应class的解码器。
- 2. 所以，协议调整为，在消息头Header前面添加4个字节(int型)的长度域。保证不会在更新长度域值是，覆盖了class信息，导致解码时找不到对应的解码器

## 2.3 Protobuf 编码

## 2.4 thrift 编码

## 2.5 Avro 编码

# 3. 多种编码性能比较

# 4. Netty相关知识补充
- 1. 如果以前未接触过Netty，可以阅读专栏[地址]()
- 2. LengthFieldBasedFrameDecoder自定义长度解码器，[博客](https://blog.csdn.net/thinking_fioa/article/details/80573483)

# 参考文档

- 1. [Netty-msg](https://github.com/tang-jie/NettyRPC)
- 2. [LengthFieldBasedFrameDecoder](http://netty.io/5.0/api/io/netty/handler/codec/LengthFieldBasedFrameDecoder.html)
- 3. [LengthFieldBasedFrameDecoder博客](https://blog.csdn.net/thinking_fioa/article/details/80573483)
- 4. 
