# netty 学习
```
@author 鲁伟林
向开源致敬，向优秀前辈代码致敬。
Netty中大量使用NIO技术，满足高性能、高并发要求。进行初步研究，特作此项目。
更新时间：未派上档期，等我的另一个rocketmq-learning项目初步完成，动工Netty源码研究
源码地址：https://github.com/thinkingfioa/netty-learning
```

## 1.为什么要读Netty？

### 1.1 Netty 特性

## 2.Netty案例学习

### 2.1 Netty私有协议开发
- 1.《Netty权威指南2》中第12章节，讲解了关于私有协议栈开发。平时开发中具有参考价值，但书本中代码存在较多问题，本人基于文中代码进行调试，成功运行。
- 2.实现了基本私有协议栈开发，并成功运行。在此基础上，比较Marshalling编码和Kryo编码的速度。
- 3.代码中实现了Marshalling和Kryo的编码
- 4.[子项目文档地址](https://github.com/thinkingfioa/netty-learning/tree/master/netty-private-protocol)

## 3. Netty 源码学习
Netty源码较复杂，本人等学习研究完RocketMQ项目(rocketmq-learning)后，开始此项目源码分析。

# 参考文档
- 1.《Netty权威指南2》