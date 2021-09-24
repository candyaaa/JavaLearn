# ByteBuf

## ByteBuf的优点

- 用户自定义缓冲区类型支持；
- 通过内置的复合缓冲区类型实现了透明的零拷贝；
- 容量可以按需增长；
- 在读和写这两种模式之间切换不需要调用 ByteBuffer 的 flip()方法；
- 读和写使用了不同的索引；
- 支持方法的链式调用；
- 支持引用计数；
- 支持池化；

## ByteBuf的实现

内部有两个index，readerIndex（读索引）、writerIndex（写索引）由这两个index分别控制ByteBuf的读和写，永远满足readerIndex<=writerIndex，当 操作试图形成readerIndex>
writerIndex时，会触发一个IndexOutOfBoundException异常。

ByteBuf以read和writer开头的方法，将会改变对应的index，而set和get操作则不会，这些方法只是在对应的index上做一些操作。

ByteBuf也有最大容量，通常是Integer.MAX,当超过这个值的时候会触发异常。

## ByteBuf的几种模式

### HeapBuffer

最常用的 ByteBuf 模式是将数据存储在 JVM 的堆空间中。这种模式被称为支撑数组 （backing array），它能在没有使用池化的情况下提供快速的分配和释放。

### DirectBuffer

将数据存储在直接内存中，是网络传输的最理想方式（不需要再从堆中copy到直接缓存区）可以直接发送，但是如果要在堆内使用DirectBuffer的数据，需要一次copy（将直接内存中的数据copy到堆中）。

### 

为多个ByteBuf提供的一个聚合视图，提供了将多个视图聚合成一个视图的能力 。