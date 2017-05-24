package org.sangaizhi.nettychat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.sangaizhi.nettychat.core.codc.RequestDecoder;
import org.sangaizhi.nettychat.core.codc.ResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sangaizhi
 * @date 2017/5/24
 */
@Component
public class Server {

	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	public void start() {
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			bootstrap.group(boss, worker);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(new RequestDecoder());
					socketChannel.pipeline().addLast(new ResponseEncoder());
					socketChannel.pipeline().addLast(new ServerHandler());
				}
			});
			bootstrap.option(ChannelOption.SO_BACKLOG, 2048);
			bootstrap.bind(10102).sync();
			logger.info("server is start!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
