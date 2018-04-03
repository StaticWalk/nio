package com.iot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by xiongxiaoyu
 * Data:2018/4/3
 * Time:13:56
 *
 *
 *
 * SocketChannel非阻塞模式（non-blocking mode）
 * 		设置之后，就可以在异步模式下调用connect(), read() 和write()了。
 * connect() 非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了
 * write() 非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。所以需要在循环中调用write()
 * read() 非阻塞模式下,read()方法在尚未读取到任何数据时可能就返回了。它的int返回值会告诉你状态和读取了多少字节
 */
public class SocketChannelDemo {

	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("http://127.0.0.1", 80));


		//用于等待finishConnect()的连接
//		while(! socketChannel.finishConnect() ){
//			//wait, or do something else...
//		}

		//从SocketChannel读取数据，-1表示读完了
//		ByteBuffer buf = ByteBuffer.allocate(48);
//		int bytesRead = socketChannel.read(buf);


		//写入数据到socketChannel，使用socketChannel.write(buf)
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
			socketChannel.write(buf);
		}



		//关闭SocketChannel
		socketChannel.close();
	}

}
