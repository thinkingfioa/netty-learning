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
- 1.netty4.1以上版本中将channelRead(...)等方法从ChannelHandlerAdapter类中移除。添加到子类: ChannelInboundHandlerAdapter中。

# 1. 私有协议开发
- 1.业务场景: 先进行Tcp连接，然后拆分发送多个数据包，实现文件传输。
- 2.不同的业务场景需要定制化不同的私有协议，本例子讲给出一个私有化协议，讲述其利用Netty的Tcp协议进行传输。采用5种不同的编解码来实现通信编码，并进行比较。

## 1.1 场景描述
时序图:

## 1.2 消息结构
私有协议共有三个部分:Header + Body + Tail。不同的消息中Header和Tail字段部分将保持一致，Body部分不同的消息将有不同的实现

|||| 
|:---:|:---:|:---:|
|Header|Body|Tail|

### 1.1.1 Header
Header属性解释如下:

|属性|类型|描述|
|:---:|:---:|:---:|
|msgLen|int|发送pkg长度|
|sessionID|long|发送方的sessionId|
|msgType|String|消息类型|
|...|...|...|

##### 代码:
```java
public class Header {
    /**
     * 发送pkg长度
     */
    private int msgLen;

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
不同的消息类型，Header和Tail部分的字段都一样，但是不同的消息类型，对应于不同字段。共有:

#### 类图如下:

### 1.1.3 Tail
Tail是消息尾部字段描述，具体如下:

## 1.2 Client端

## 1.3 Server端

# 2. 编解码

## 2.1 Marshalling 编码

### 2.1.1 pom依赖
```
<!-- marshalling  -->
<dependency>
    <groupId>org.jboss.marshalling</groupId>
    <artifactId>jboss-marshalling</artifactId>
    <version>2.0.0.Final</version>
</dependency>
```

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

## 2.3 Protobuf 编码

## 2.4 thrift 编码

## 2.5 Avro 编码

## 2.6 二进制编码

# 3. 多种编码性能比较

# 4. Netty相关知识补充


# 参考文档
