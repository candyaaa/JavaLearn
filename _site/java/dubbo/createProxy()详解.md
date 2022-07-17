# CreateProxy详解

## 整体流程

- 通过持有远程服务实例生成Invoker
- 把Invoker通过动态代理转换成实现用户接口的引用

Invoker承载了网络连接、服务调用、重试等功能，在客户端，它可能是一个远程的实现，也可能是一个集群的实现。

## 细节

入口在ReferenceBean#getObject，不管是XMl还是注解，都会转换成ReferenceBean，它集成自ReferenceConfig，

Dubbo支持多注册中心同时消费，如果是多注册中心，则会在ReferenceConfig#createProxy中合并成一个Invoker

### ReferenceConfig#createProxy
```java

```