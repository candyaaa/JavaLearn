# SSL/TLS协议

这个暂时理解成一种加密的手段，对应SslHandler能实现数据安全，SSL/TLS的原理以后学习了可以再补充。

## HTTP/HTTPS应用

## HTTP聚合

由于 HTTP 的请求和响应可能由许多部分组成，因此你需要聚合它们以形成完整的消息。为了消除这项繁琐的任务，Netty 提供了一个聚合 器，它可以将多个消息部分合并为 FullHttpRequest 或者 FullHttpResponse
消息。

## HTTP压缩

当使用 HTTP 时，建议开启压缩功能以尽可能多地减小传输数据的大小。Netty 为压缩和解压缩提供了 ChannelHandler 实现，它们同时支持 gzip 和 deflate 编码。

## HTTPS

启用 HTTPS 只需要将 SslHandler 添加到 ChannelPipeline 的 ChannelHandler 组合中。

## WebSocket

略

## 空闲的链接和超时

- IdleStateHandler：当连接空闲时间过长，将触发一个IdleStateHandler事件。

## 写大型数据

TODO...