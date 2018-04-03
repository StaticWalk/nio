package com.iot;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xiongxiaoyu
 * Data:2018/4/3
 * Time:13:09
 *
 *
 * FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。FileChannel无法设置为非阻塞模式，运行在阻塞模式下。
 *
 * 可以使用FileChannel.truncate()方法截取一个文件。截取文件时，文件将中指定长度后面的部分将被删除  channel.truncate(1024);
 *
 * FileChannel实例的size()方法将返回该实例所关联文件的大小  long fileSize = channel.size();
 *
 * FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。出于性能考虑，操作系统会将
 * 		数据缓存在内存中，无法保证写入到FileChannel里的数据会即时写到磁盘上。
 * 		channel.force(true);
 */
public class FileChannelDemo {

	public static void main(String[] args) throws IOException {

		//通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例
		RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		//从FileChannel读取数据，调用多个read()从FileChannel中读取数据。返回buf的字节数
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf);



		//position(),FileChannel特定位置的数据读写操作
		//如果将位置设置在文件结束符之后，然后试图从文件通道中读取数据，读方法将返回文件结束标志-1
		//如果将位置设置在文件结束符之后，然后向通道中写数据，文件将撑大到当前位置并写入数据。这可能导致“文件空洞”，磁盘上物理文件中写入的数据间有空隙。
//		long pos = channel.position();
//		channel.position(pos +123);



		//使用FileChannel.write()方法向FileChannel写数据，该方法的参数是一个Buffer
//		String newData = "New String to write to file..." + System.currentTimeMillis();
//		ByteBuffer buf = ByteBuffer.allocate(48);
//		buf.clear();
//		buf.put(newData.getBytes());
//		buf.flip();
//		while(buf.hasRemaining()) {
//			channel.write(buf);
//		}

		//使用FileChannel后必须关闭
		inChannel.close();


	}

}
