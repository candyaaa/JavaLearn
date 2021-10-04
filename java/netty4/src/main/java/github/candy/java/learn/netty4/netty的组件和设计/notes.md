## Channel、EventLoop、ChannelFuture
> Channel、EventLoop、ChannelFuture可以被认为是Netty对网络抽象的代表
- Channel——Socket
- EventLoop——控制流、多线程处理、并发处理
- ChannelFuture——异步通知
## Channel接口 
基本的 I/O 操作（bind()、connect()、read()和 write()）依赖于底层网络传输所提供的原语。
在基于Java的网络编程中，其基本的构造是Socket。但是在Netty所提供的API中，大大降低了直接使用
Socket的复杂性。
## EventLoop
EventLoop 定义了 Netty 的核心抽象，用于处理连接的生命周期中所发生的事件。






## Channel、EventLoop、EventGroup的工作流程
 