 &emsp;&ensp;NIO(三个核心组件：Channels  Buffers Selectors)：   
&emsp;&ensp;java NIO(New IO)是一个可以替代Java IO API(after 1.4),提出了不同于标准IO的新工作方式
 * Channels and Buffers 标准IO基于字节流和字符流操作，NIO基于通道Channels和缓冲区Buffer操作，数据总是从通道读取到缓冲区，缓冲区写入到通道中。  
 * Non-blocking IO NIO可以非阻塞使用IO，例如：线程从通道读取数据到缓冲区时，线程能进行其他事情。数据被写入缓冲区时，线程可以继续处理它。缓冲区写入通道类似。  
 * Selectors选择器 NIO引入了选择器的概念，选择器用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单个的线程可以监听多个数据通道  
 * Channels  FileChannel,DatagramChannel,SocketChannel,ServerSocketChannel  
 * Buffers  ByteBuffer,CharBuffer,DoubleBuffer,FloatBuffer,IntBuffer,LongBuffer,ShortBuffer,MappedByteBuffer  
 * Selectors  使用Selector，先向Selector注册Channel，然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新连接进来，数据接收等。  
 