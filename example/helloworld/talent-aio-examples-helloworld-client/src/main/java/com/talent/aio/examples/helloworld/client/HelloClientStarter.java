/**
 * **************************************************************************
 *
 * @说明: 
 * @项目名称: talent-aio-examples-server
 *
 * @author: tanyaowu 
 * @创建时间: 2016年11月17日 下午5:59:24
 *
 * **************************************************************************
 */
package com.talent.aio.examples.helloworld.client;

import java.io.IOException;

import com.talent.aio.client.AioClient;
import com.talent.aio.client.ClientChannelContext;
import com.talent.aio.client.ClientGroupContext;
import com.talent.aio.client.intf.ClientAioHandler;
import com.talent.aio.client.intf.ClientAioListener;
import com.talent.aio.common.Aio;
import com.talent.aio.examples.helloworld.common.HelloPacket;

/**
 * 
 * @author tanyaowu 
 * @创建时间 2016年11月17日 下午5:59:24
 *
 * @操作列表
 *  编号	| 操作时间	| 操作人员	 | 操作说明
 *  (1) | 2016年11月17日 | tanyaowu | 新建类
 *
 */
public class HelloClientStarter
{
	private static String serverIp = null; //服务器的IP地址
	private static int serverPort = 0; //服务器的PORT
	private static AioClient<Object, HelloPacket, Object> aioClient;
	private static ClientGroupContext<Object, HelloPacket, Object> clientGroupContext = null;
	private static ClientAioHandler<Object, HelloPacket, Object> aioClientHandler = null;
	private static ClientAioListener<Object, HelloPacket, Object> aioListener = null;

	public static String SERVER_IP = "127.0.0.1"; //服务器的IP地址
	public static int SERVER_PORT = 9321; //服务器的PORT

	public static void main(String[] args) throws Exception
	{
		serverIp = "127.0.0.1";
		serverPort = com.talent.aio.examples.helloworld.common.Const.PORT;
		aioClientHandler = new HelloClientAioHandler();
		aioListener = null;
		clientGroupContext = new ClientGroupContext<>(serverIp, serverPort, aioClientHandler, aioListener);
		aioClient = new AioClient<>(clientGroupContext);

		String bindIp = null;
		int bindPort = 0;
		boolean autoReconnect = false; //暂时不支持自动重连，需要业务自己实现，后续版本会支持此属性为true
		ClientChannelContext<Object, HelloPacket, Object> clientChannelContext = aioClient.connect(bindIp, bindPort, autoReconnect);

		
		//以下内容不是启动的过程，而是属于发消息的过程
		HelloPacket packet = new HelloPacket();
		packet.setBody("hello world".getBytes(HelloPacket.CHARSET));
		Aio.send(clientChannelContext, packet);
	}
}
