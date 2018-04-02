package com.iot;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xiongxiaoyu
 * Data:2018/4/2
 * Time:15:50
 *
 *  可以只是用一个线程处理所有通道，一个线程对应一个Selector，处理多个channel
 *
 * 与Selector一起使用时，Channel必须处于非阻塞模式下。
 * 这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
 *
 * SelectionKey interest集合 ready集合 Channel  Selector  附加对象
 * 访问这几个参数的方法：集合使用“位或”，其余直接获取
 *
 * 某一信息通过SelectionKey附着，这样就能方便的识别某个给定的通道。例如，可以附加与通道一起使用的Buffer，或是包含聚集数据的某个对象。
 * selectionKey.attach(theObject);
 * Object attachedObj = selectionKey.attachment();
 *
 * 用register()方法向Selector注册Channel的时候附加对象
 * SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);
 */
public class Selector {

	public static void main(String[] args) throws IOException {


		//Selector.open()注册一个Selector
		java.nio.channels.Selector selector= java.nio.channels.Selector.open();

		//使用Selector时，Channel必须处于非阻塞模式，FileChannel不能切换非阻塞模式，套接字通道可以。
		//将channel注册到selector上，通过SelectableChannel.register()实现，第二个参数是“interest集合”
		//interest集合: SelectionKey.OP_READ,OP_ACCEPT,OP_CONNECT,OP_WRITE，可以使用“位或”操作符
		channel.configureBlocking(false);

		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

		while(true) {
			int readyChannels = selector.select();
			if(readyChannels == 0) continue;
			Set selectedKeys = selector.selectedKeys();
			Iterator keyIterator = selectedKeys.iterator();
			while(keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if(key.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (key.isConnectable()) {
					// a connection was established with a remote server.
				} else if (key.isReadable()) {
					// a channel is ready for reading
				} else if (key.isWritable()) {
					// a channel is ready for writing
				}
				keyIterator.remove();
			}
		}

	}
}
