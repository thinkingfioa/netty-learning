# netty-private-protocol 开发
```
@author 鲁伟林
工作量:
1. 《Netty权威指南2》中第12章节，详细讲解了私有协议开发，内容。实际项目中，我们常常需要定制化私有协议。
2. 《Netty权威指南2》书中代码存在较多问题，无法运行。我经过研究，修改了书中代码，并成功运行。
3. 成功运行基础上，加入了Kryo编码，与书本中提供的Marshalling编码进行对比。
4. 实验表明，Kryo编码速度的确高于Marshlling编码速度。能够加快Netty的传输速度。
5. 给出6种编码方式在Netty下的使用

GitHub地址: https://github.com/thinkingfioa/netty-learning/tree/master/netty-private-protocol
本人博客地址: http://blog.csdn.net/thinking_fioa/article/details/78265745
```

## 提醒
- 1.netty4.1以上版本中将channelRead(...)等方法从ChannelHandlerAdapter类中移除。添加到子类: ChannelInboundHandlerAdapter中。

# 1. 私有协议开发

## 1.1 消息结构

### 1.1.1 Header

### 1.1.2 Body

### 1.1.3 Tail

## 1.2 Client端

## 1.3 Server端

# 2. 编解码

## 2.1 Marshalling 编码

## 2.2 Kryo 编码

## 2.3 Protobuf 编码

## 2.4 thrift 编码

## 2.5 Avro 编码

## 2.6 二进制编码

#3. 多种编码性能比较

# 参考文档
