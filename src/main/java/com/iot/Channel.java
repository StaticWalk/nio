package com.iot;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by xiongxiaoyu
 * Data:2018/4/2
 * Time:15:01
 *
 *
 *  NIO中，如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel传输到另外一个channel。
 *  	FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中。
 *		transferTo()方法将数据从FileChannel传输到其他的channel中。
 *
 *
 */
public class Channel {


	public static void main(String[] args) throws IOException {

		/**
		 * FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中
		 *
		 * 在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。
		 *		因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。
		 */
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		long position = 0;
		long count = fromChannel.size();
		toChannel.transferFrom(fromChannel,position, count);

		/**
		 * 确实算一种新IO模式，以前的文件操作代码很复杂，简化代码，有一点java基础的理解起来很轻松
		 *
		 * 在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。
		 * 		因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。
		 */
//		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
//		FileChannel fromChannel = fromFile.getChannel();
//		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
//		FileChannel toChannel = toFile.getChannel();
//		long position = 0;
//		long count = fromChannel.size();
//		fromChannel.transferTo(position, count, toChannel);
	}
}
