## 阻塞IO

### 操作系统层面

当系统调用recvfrom读取数据时，系统调用直到数据包到达且被复制到应用缓冲区中或者报错，才会返回，这个期间一直处于阻塞状态。 系统从调用到被返回都是处于阻塞状态称为阻塞IO。

### JAVA层面

BIO通常由一个独立的Acceptor线程来负责客户端的连接，当它接收到一个连接，会为该客户端创建一个单独的线程，去进行链路处理。
处理完成之后通过输入流，返回响应应答给客户端，然后线程销毁，这就是典型的一对一请求应答模型，也就是我们常说的BIO。

## 非阻塞IO

### 操作系统层面

当系统调用recvfrom读取数据时，如果缓冲区没有数据的话，则直接会返回一个EWODULDBLOCK错误，不要让任务一直等待，直接返回。 那么也意味着如果应用要读取到数据，要不断的重试。

## IO复用

如果在高并发的场景下，这么多的线程不断调用recvfrom请求数据，这显然是不可以的。 所以出现了单个线程通过select、poll、epoll函数来监控多个fd，从而不要为每个客户端创建一个线程，从而减少线程的数量。

## 信号驱动IO

IO复用虽然可以通过一个线程来监听多个fd了，但是它的本质还是通过不断轮询来监控数据的状态，其实大部分的轮询是无意义的， 信号驱动就是通过这种建立信号关联的方式，实现了发出请求后只需要等待数据就绪的通知即可。

专业术语：首先开启套接口信号驱动IO功能，并通过系统调用sigaction执行一个信号处理函数，此时请求即刻返回，当数据准备就绪时，就生成对应进程的SIGIO信号，通过信号回调通知应用线程调用recvfrom来读取数据。

## 异步IO

专业术语： 应用告知内核启动某个操作，并让内核在整个操作完成之后，通知应用，这种模型与信号驱动模型的主要区别在于，信号驱动IO只是由内核通知我们合适可以开始下一个IO操作，而异步IO模型是由内核通知我们操作什么时候完成。

## ref

- <a>https://zhuanlan.zhihu.com/p/115912936
- <<Netty权威指南第二版>>






