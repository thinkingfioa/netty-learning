# Netty-Small-Demo
```
@author 鲁伟林
子项目Netty-Small-Demo介绍诸多使用Netty的小案例。如: ChannelHandler动态编排、AttributeMap的使用或ChannelPrimise等诸多Netty提供的特性。
非常欢迎同学们fork或者留言，一起交流技术。
本博客中涉及的完整代码：
GitHub地址: https://github.com/thinkingfioa/netty-learning/tree/master/netty-small-demo。
本人博客地址: https://blog.csdn.net/thinking_fioa
```

## 1. 项目的案例介绍
Netty-Samll-Demo子项目，基于Netty的多个特性，实现多种案例。介绍如下:

|案例|作用|
|:---:|:---:|
|UDP传输|实现Netty的UDP传输机制|
|ChannelHandler动态编排|根据实际业务场景，动态编排ChannelHandler|
|AttributeMap使用|利用ChannelAttributeMap，实现多个ChannelHandler传递变量|
|Netty与多种编码器的使用|基于Netty和多种编码节技术，实现自定义协议开发|
|ChannelGroup使用|Netty的ChannelGroup管理Channel|
|channel.writeAndFlush与ctx.writeAndFlush区别|案例分析两者不同点|

## 2. UDP传输

## 3. ChannelHandler动态编排
Netty提供用户事件触发: userEventTriggered特性，实现ChannelHandler动态编排。

### 3.1 场景
实际项目开发中，存在这样两个问题:

1. 创建Channel对象时，无法明确的知道需要添加到ChannelPipeline链上的具体ChannelHandler有哪些？具体ChannelHandler往往是运行时才能知道。
2. 一次性将所有的ChannelHandler添加到ChannelPipeline链上往往与设计不符。比如：用户先完成认证延签后，采用发起用户登录。用户完成登录，才能发起消息类型订阅。

### 3.2 项目介绍
ChannelHandler动态编排

## TODO LIST

-  UDP传输
-  ChannelHandler动态编排
-  AttributeMap使用
-  Netty与protobuf的使用
-  ChannelGroup使用
-  文件传输性能对比
-  dom4j
-  channel.writeAndFlush与ctx.writeAndFlush区别