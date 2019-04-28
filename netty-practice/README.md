# Netty-Practice
```
@author 鲁伟林
项目Netty-Practice介绍Practice介绍诸多使用Netty的特性，并辅助案例帮助理解。
对使用Netty框架作为开发的大有好处
非常欢迎同学们fork或者留言，一起交流技术。

代码地址：
GitHub地址: https://github.com/thinkingfioa/netty-learning/tree/master/netty-practice
博客地址: https://blog.csdn.net/thinking_fioa
```

## 项目的案例介绍
Netty-Practice子项目，基于Netty的多个特性，实现多种案例。介绍如下:

|索引|案例|作用|
|:---:|:---:|:---:|
|1|UDP传输|实现Netty的UDP传输机制|
|2|ChannelHandler动态编排|根据实际业务场景，动态编排ChannelHandler|
|3|AttributeMap使用|利用ChannelAttributeMap，实现多个ChannelHandler传递变量|
|4|Netty与多种编码器的使用|基于Netty和多种编码节技术，实现自定义协议开发|
|5|ChannelGroup使用|Netty的ChannelGroup管理Channel|
|6|channel.writeAndFlush与ctx.writeAndFlush区别|案例分析两者不同点|

## 1. UDP传输

## 2. ChannelHandler动态编排
Netty提供用户事件触发: userEventTriggered特性，实现ChannelHandler动态编排。案例代码Package: package org.lwl.netty.dynamic。如果想阅读源码，建议下载源码，导入idea中阅读。阅读过程中，欢迎交流

1. 项目源码: [地址](https://github.com/thinkingfioa/netty-learning/tree/master/netty-practice/src/main/java/org/lwl/netty/dynamic)
2. 项目包名: org.lwl.netty.dynamic
3. 项目Main类: DynamicDemoStart

### 2.1 场景
实际项目开发中，客户端存在这样两个问题:

1. 创建Channel对象时，无法明确的知道需要添加到ChannelPipeline链上的具体ChannelHandler有哪些？具体ChannelHandler往往是运行时才能知道。
2. 一次性将所有的ChannelHandler添加到ChannelPipeline链上往往与设计不符。比如：用户先完成认证延签后，采用发起用户登录。用户完成登录，才能发起消息类型订阅。

### 2.2 项目介绍
ChannelHandler动态编排项目，主要讲解了如何通过userEventTriggered特性，来实现动态向ChannelPipeline加入ChannelHandler。项目主要模拟https单向认证的过程，Server端一次性添加所有的ChannelHandler到ChannelPipeline上，Client端分下列步骤加入ChannelHandler

1. 发送Client端SSL版本等信息。  ----- SslHandler
2. 发送Client端支持的对称加密方案。  ----- SymEncryptionHandler
3. 产生随机码作为对称加密密钥，使用服务端的公钥对随机码加密，发送给服务端。 ----- RandomCodeHandler
4. 发送加密后登录消息消息。 ----- LoginHandler
5. 登录成功后加入心跳Handler ----- HeartbeatClientHandler

### 2.3 https单向认证序列图
![](./docs/pics/dynamic-ssl.png)

### 2.4 项目讲解
1. ChannelHandler动态编排中使用较为简单的编解码器，如果想深入学习Netty多种编解码方式，请看[netty-private-protocol子项目](https://github.com/thinkingfioa/netty-learning/tree/master/netty-private-protocol)。
2. 消息采用简单的消息格式。项目主要想表明如果实现ChannelHandler动态编排机制，https单项认证部分只是一个简单的框架，点到为止。
3. 项目使用Netty提供的LengthFieldBasedFrameDecoder来解决粘包粘包问题，每条消息头部添加4个字节的长度字段。可[参考博客](https://blog.csdn.net/thinking_fioa/article/details/80573483)来配置粘包粘包参数
4. 项目的编解码使用较为简单的编解码，如果想了解多种netty编解码使用，可以[参考博客待补充](https://blog.csdn.net/thinking_fioa)
5. 项目启动类: DynamicDemoStart.java

### 2.5 运行结果图
无，终端输出可见

### 2.6 客户端ChannelHandler介绍

1. ClientInitHandler ----- 客户端初始Handler，向Pipeline添加SslHandler
2. SslHandler ----- 发送SSL版本信息。收到服务端回复后，向Pipeline添加SymEncryptionHandler
3. SymEncryptionHandler ----- 发送对称加密方案。收到服务端回复后，向Pipeline添加RandomCodeHandler
4. RandomCodeHandler ----- 发送随机码。同时向Pipeline添加LoginHandler
5. LoginHandler ----- 发送登录请求。收到登录响应后，向Pipeline添加HeartbeatClientHandler
6. HeartbeatClientHandler ----- tcp心跳读写事件处理
7. DynamicTriggerHandler ----- 处理用户自定义添加ChannelHandler事件请求

## 3. ctx.write(...)和channel.write(...)区别案例


## TODO LIST

-  UDP传输
-  ChannelHandler动态编排
-  AttributeMap使用
-  Netty与protobuf的使用
-  ChannelGroup使用
-  文件传输性能对比
-  dom4j
-  channel.writeAndFlush与ctx.writeAndFlush区别
-  log4j2自定义日志级别
-  websocket

# 参考:
1. [Https单向认证和双向认证](https://blog.csdn.net/duanbokan/article/details/50847612)