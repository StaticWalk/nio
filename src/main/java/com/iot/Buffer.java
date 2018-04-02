package com.iot;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xiongxiaoyu
 * Data:2018/4/2
 * Time:13:10
 *
 *
 * Buffer对象用于和NIO通道进行交互，缓冲区本质是一块可以写入数据，可以读取数据的内存。
 * 使用Buffer读写数据一般遵循以下四个步骤：
 * 		写入数据到Buffer -> 调用flip()方法 -> 从Buffer中读取数据 -> 调用clear()方法或者compact()方法(清除已读过的缓存)
 * 三个属性capacity,position和limit，后两者含义取决于是读或写模式。
 * 		写数据到buffer，position=当前位置，初始值=0，max=capacity-1。limit表示最多能往buffer里面写多少数据,limit=capacity。
 * 		从buffer读数据，当从写模式切换到读模式时，position置0，读取数据时，position向前移动到下一个可读的位置。limit=写模式下的position值。
 * buffer.get() put() equals() compareTo() clear() compact()
 * 		mark()标记某一个position   reset()回到mark标记的position   rewind()将position指0，可以实现重读buffer中所有数据。
 *
 */
public class Buffer {


	public static void main(String[] args) throws IOException {

//		1.
		RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
//create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf); //read into buffer.
		while (bytesRead != -1) {
			buf.flip();  //make buffer ready for read
			while(buf.hasRemaining()){
				System.out.print((char) buf.get()); // read 1 byte at a time
			}
			buf.clear(); //make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}
}
