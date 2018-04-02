package com.iot;

/**
 * Created by xiongxiaoyu
 * Data:2018/4/2
 * Time:14:31
 *
 * Scatter/Gather是对通道读写操作的描述，
 * 		Gather聚集：写入channel的时候是将多个buffer数据聚集在一起后写入同一个channel。
 * 			不适用于动态消息，如果存在消息头和消息体，消息头必须完成填充（例如 128byte），Scattering Reads才能正常工作。
 * 		Scatter分散：从channel中读取出的数据会被分散写入到多个buffer中。
 *
 */
public class ScatterGather {

	/**
	 * 创建一个多个buffer组成的buffer数组，channel中数据会依次读取到bufferArray中，
	 * Scattering Reads填满当前buffer后才能移向下一个，
	 */
//	ByteBuffer header = ByteBuffer.allocate(128);
//	ByteBuffer body   = ByteBuffer.allocate(1024);
//	ByteBuffer[] bufferArray = { header, body };
//	channel.read(bufferArray);


	/**
	 * 依次写入，只有position到limit之间有效数据才写入，
	 * Gathering Writes能较好的处理动态消息
	 */
//	ByteBuffer header = ByteBuffer.allocate(128);
//	ByteBuffer body   = ByteBuffer.allocate(1024);
//	ByteBuffer[] bufferArray = { header, body };
// 	channel.write(bufferArray);
}
