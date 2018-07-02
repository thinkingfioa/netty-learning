# netty-private-protocol 开发
```
@author 鲁伟林
工作量:
1. 《Netty权威指南2》中第12章节，详细讲解了私有协议开发，内容。实际项目中，我们常常需要定制化私有协议。
2. 《Netty权威指南2》书中代码存在较多问题，无法运行。我经过研究，修改了书中代码，并成功运行。
3. 成功运行基础上，加入了Kryo编码，与书本中提供的Marshalling编码进行对比。
4. 实验表明，Kryo编码速度的确高于Marshlling编码速度。能够加快Netty的传输速度。
5. 给出6种编码方式在Netty下的使用，并给出详细的案例。
6. 可以学习多种编码器在项目中如何使用，及他们的特性。

GitHub地址: https://github.com/thinkingfioa/netty-learning/tree/master/netty-private-protocol
本人博客地址: http://blog.csdn.net/thinking_fioa/article/details/78265745
```

## 提醒
- 1. netty4.1以上版本中将channelRead(...)等方法从ChannelHandlerAdapter类中移除。添加到子类: ChannelInboundHandlerAdapter中。
- 2. Kryo编码中使用writeClassAndObject()方法序列对象时，会先写入相关的Class信息。所以，不能讲消息的长度字段(msgLen)放在Header头中。
- 3. 使用Netty时，不要阻塞I/O线程。同时，尽量不要使用sync同步方法，请使用异步Listening传递异步执行结果。


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
- 3. 使用protobuf编码时，由于protobuf不支持Java的short/byte类型，所以协议部分字段类型进行修改，见2.4.3节。其他编码器变
- 4. Header属性解释如下:

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

## 1.3 LengthFieldBasedFrameDecoder的使用
协议中使用的自定义长度解码器是: LengthFieldBasedFrameDecoder。LengthFieldBasedFrameDecoder解码器自定义长度解决TCP粘包黏包问题。所以LengthFieldBasedFrameDecoder又称为: 自定义长度解码器。私有化协议中的参数是

- 1. lengthFieldOffset = 0
- 2. lengthFieldLength = 4
- 3. lengthAdjustment = -4 = 数据包长度(msgLen) - lengthFieldOffset(0) - lengthFieldLength(4) - msgLen
- 4. initialBytesToStrip = 0

关于LengthFieldBasedFrameDecoder的理解，可参考博客[地址](https://blog.csdn.net/thinking_fioa/article/details/80573483)

## 1.4 Client端

## 1.5 Server端

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

## 2.1 几种编解码器支持的语言
几种编码器支持的语言各有不同，Y-支持，N-不支持

|编解码器|java|C#|c++|go|python|
|:---:|:---:|:---:|:---:|:---:|:---:|
|Marshalling|Y|N|N|N|N|
|Kryo|Y|N|N|N|N|
|protobuf|Y|Y|Y|Y|Y|
|thrift|Y|Y|Y|Y|Y|
|Avro|Y|N|N|N|N|


## 2.2 Marshalling 编码

### 2.2.1 pom依赖
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

### 2.2.2 Marshalling 编码讲解
 - 1. Marshalling编码对应于代码中的package org.lwl.netty.codec.marshalling;
 - 2. Marshalling主要用于对Object进行编码。对于基础的数据类型:List、Map、Integer等直接使用ByteBuf的writeXXX方法编码

## 2.3 Kryo 编码

### 2.3.1 pom依赖
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
### 2.3.2 Kryo注意事项

- 1. writeClassAndObject(...)方法会写入class的信息。设计协议时，如果将长度域放在Header中，那么将会导致Kryo解码时，找不到对应class的解码器。
- 2. 所以，协议调整为，在消息头Header前面添加4个字节(int型)的长度域。保证不会在更新长度域值是，覆盖了class信息，导致解码时找不到对应的解码器。

## 2.4 Protobuf 编码
protobuf是Google开源的工具，有诸多非常优秀的特性:

- 1. 与平台无关，与语言无关，可扩展。支持的语言非常多[官方地址](https://github.com/google/protobuf)
- 2. 性能优秀，速度是Xml的20-100倍
- 3. 需要编写中间proto文件，这点对使用者不太友好

### 2.4.1 安装
Protobuf是通过C++编写的。mac电脑，安装步骤如下:

- 1. 使用了brew安装automake和libtool。命令如下
- 2. 下载版本: protobuf-java.3.6.0.tar.gz。[地址](https://github.com/google/protobuf/releases)。不要下载源码编译，我们可以直接下载releases。目前protobuf最新的是3.6.0版本
- 3. 执行make和make install即可
- 4. 验证安装是否成功: protoc --version

##### 代码:
```shell
// 第一步，安装automake和libtool
1. brew install automake
2. brew install libtool

// 第二步, 下载release包

// 第三步, 安装
3. make
4. make install

// 第四步, 验证
5. protoc --version
```

### 2.4.2 使用
- 1. 编写proto，具体语法可[参考](https://blog.csdn.net/fangxiaoji/article/details/78826165)。大家注意一点是protobuf3.0版本语法与2.0好像差距蛮大的。
- 2. 使用命令: protoc -I=proto文件所在的目录 --java_out=生成java文件存放地址。如netty-private-protocol子项目的命令是: protoc -I=../proto/ --java_out=../../java/ 名字.proto。可直接使用Resources/bin下的脚本: buildProto.sh

##### proto编写规则
```
syntax = "proto3";
option java_package = "org.lwl.netty.message.protobuf";
option java_outer_classname="Test";

message Message {
    string name = 1;
    string age = 2;
}
```
解释:

- 1. syntax = "proto3"; ----- 申明句法的版本号。如果不指定，默认是:syntax="proto2"
- 2. java_package ----- 生成的类包路径
- 3. java_outer_classname ----- 生成的数据访问类的类名
- 4. 每个field后面是标识号，必须是数值，如: string name = 1;

### 2.4.3 声明
由于protobuf与Java的数据类型存在较大不同点，所以对协议中的字段部分类型修改。

- 1. protobuf不支持Java中的Short和Byte类型，用int代替。如Header中的域: flag和oneByte，还有map的value类型都是String

### 2.4.4 pom依赖
```
<dependency>
    <groupId>com.google.protobuf</groupId>
    <artifactId>protobuf-java</artifactId>
    <version>3.6.0</version>
</dependency>
```

## 2.5 thrift 编码

## 2.6 Avro 编码

# 3. 多种编码性能比较

# 4. Netty相关知识补充
- 1. 如果以前未接触过Netty，可以阅读专栏[地址]()
- 2. LengthFieldBasedFrameDecoder自定义长度解码器，[博客](https://blog.csdn.net/thinking_fioa/article/details/80573483)

# 参考文档

- 1. [Netty-msg](https://github.com/tang-jie/NettyRPC)
- 2. [LengthFieldBasedFrameDecoder](http://netty.io/5.0/api/io/netty/handler/codec/LengthFieldBasedFrameDecoder.html)
- 3. [LengthFieldBasedFrameDecoder博客](https://blog.csdn.net/thinking_fioa/article/details/80573483)
- 4. [Mac上protobuf安装](https://blog.csdn.net/wwq_1111/article/details/50215645)
- 5. [protobuf 3.5语法](https://blog.csdn.net/fangxiaoji/article/details/78826165)
- 6. [protobuf3语言指南](https://blog.csdn.net/u011518120/article/details/54604615)
