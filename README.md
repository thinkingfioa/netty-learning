# netty 学习
```
@author 鲁伟林
向开源致敬，向优秀前辈代码致敬。
Netty中大量使用NIO技术，满足高性能、高并发要求。进行初步研究，特作此项目。

源码地址：https://github.com/thinkingfioa/netty-learning
本人博客地址: https://blog.csdn.net/thinking_fioa
```

TODO

1. 动态编码ChannelHandler
2. AttributeMap使用
3. blog: Channel的理解。包括死锁问题
4. udp项目
5. Netty 接收缓冲区
6. Netty实现RPC框架
7. Netty.4x用户指南

## 一、为什么要读Netty？

## 1. Netty是什么？

## 2. Netty特性

## 二、 项目结构介绍

|序号|名称|介绍|
|:---:|:---:|:---:|
|1|netty-private-protocol|基于Netty自定义私有协议的开发|
|2|netty-in-action|阅读《Netty实战》的笔记，记录诸多Netty的特性和自己的理解|
|3|netty-rpc|使用Netty实现RPC框架|
|4|netty-practice|基于Netty中的诸多特性编写案例，帮助理解|

## 1. 私有协议开发(netty-private-protocol)
netty-private-protocol是一个利用Netty实现自定义的协议开发，具有非常高的普世参考价值。参考了《Netty权威指南2》中第12章节。并做以下改进:

- 1.《Netty权威指南2》中第12章节，讲解了关于私有协议栈开发。平时开发中具有参考价值，但书本中代码存在较多问题，本人基于文中代码进行调试，成功运行。
- 2.实现了基本私有协议栈开发，并成功运行。在此基础上，比较多种编码和解码的速度。
- 3.代码中实现了使用了多种编码和解码逻辑。其中有: Marshalling、Kryo、Protobuf、Thrift和messagePack。通过子项目可以学习基于Netty，实现多种编解码器技术。
- 4.[子项目文档地址](https://github.com/thinkingfioa/netty-learning/tree/master/netty-private-protocol)

## 2. Netty实战
《Netty实战》是一本好书。讲解非常透彻，能帮助开发人员更好的理解和使用Netty。最近在读第二遍。推荐此书，讲的非常透彻。

1. 总结书中多处知识点，方便开发人员理解和使用。同时添加多处自己的理解。如有错误，请指教
2. [子项目文档地址](https://github.com/thinkingfioa/netty-learning/tree/master/netty-in-action)
3. 本书分成多个章节，发布于本人的博客专栏，欢迎各位指出不足之处。[Netty专栏地址](https://blog.csdn.net/column/details/22861.html)

## 3. RPC框架实现
基于Netty，实现基本RPC框架

## 4. Netty-Practice
Netty-Practice介绍诸多使用Netty的特性，并辅助案例帮助理解。如: ChannelHandler动态编排、AttributeMap的使用或ChannelPrimise等诸多Netty提供的特性。
详见(地址)[https://github.com/thinkingfioa/netty-learning/tree/master/netty-practice]

## 三、Netty 源码学习
Netty源码研究

# 参考文档
- 1.《Netty权威指南2》