package netty.sendorder.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoInHandler1 extends ChannelInboundHandlerAdapter {


	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("in1");
		 // 通知执行下一个InboundHandler
        ctx.fireChannelRead(msg);
	}


	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();//刷新后才将数据发出到SocketChannel
	}


	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
