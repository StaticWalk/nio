package com.iot;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by xiongxiaoyu
 * Data:2018/4/3
 * Time:14:21
 */
public class ServerSocketChannelDemo {


	//非阻塞模式
	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8080));
		serverSocketChannel.configureBlocking(false);
		while(true){
			SocketChannel socketChannel =serverSocketChannel.accept();
			if (socketChannel!=null){
				System.out.println("sasas");
				//do something with socketChannel...
			}
		}
//		serverSocketChannel.close();//关闭ServerSocketChannel
	}
}
