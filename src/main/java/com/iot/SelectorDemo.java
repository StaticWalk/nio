package com.iot;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
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
 * SelectionKey interest集合 ready集合 Channel  SelectorDemo  附加对象
 * 访问这几个参数的方法：集合使用“位或”，其余直接获取
 *
 * 某一信息通过SelectionKey附着，这样就能方便的识别某个给定的通道。例如，可以附加与通道一起使用的Buffer，或是包含聚集数据的某个对象。
 * selectionKey.attach(theObject);
 * Object attachedObj = selectionKey.attachment();
 *
 * 用register()方法向Selector注册Channel的时候附加对象。register()方法会返回一个SelectionKey对象，
 * SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);
 *
 * 通过Selector选择通道 -> 调用重载的select()方法来返回你需要的(连接，接收，读或写)已经准备就绪的通道。
 * 		select()返回自上次select()操作后有多少通道变成就绪状态，
 * 		select(long time)阻塞进行的，如果没有准备就绪的通道会阻塞；selectNow()执行非阻塞选择操作。
 *
 * selectedKeys()已选择键集，成功调用select()方法后，通过Set selectedKeys=selector.selectedKeys()返回已选择键集中的通道
 *
 * wakeUp()  Selector.wakeup()立马返回阻塞在select()方法上的线程。
 *
 * close() Selector调用后，其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭
 */
public class SelectorDemo {

	public static void main(String[] args) throws IOException {


		//SelectorDemo.open()注册一个Selector
		Selector selector= Selector.open();

		//使用Selector时，Channel必须处于非阻塞模式，FileChannel不能切换非阻塞模式，套接字通道可以。
		//将channel注册到selector上，通过SelectableChannel.register()实现，第二个参数是“interest集合”
		//interest集合: SelectionKey.OP_READ,OP_ACCEPT,OP_CONNECT,OP_WRITE，可以使用“位或”操作符
		channel.configureBlocking(false);
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

		while(true) {
			int readyChannels = selector.select();
			if(readyChannels == 0) continue;

			//遍历已选择键集合来访问就绪的通道
			//SelectedKey是SelectionKey的集合
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
				//Selector不会自己从已选择键集中移除SelectionKey，处理完通道时自己移除；该通道下次就绪会再被加入已选择键集
				keyIterator.remove();
			}


		}

	}
}
