package org.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.apache.log4j.Logger;

import java.util.concurrent.Executors;


/**
 * @Author: Hao Qin
 * @Date: 19-7-25  下午7:46
 * @Version 1.0
 */
public class server {


    private static Logger log = Logger.getLogger(server.class);
    //https://blog.csdn.net/xiangzhihong8/article/details/52029446
    public void startinbound(int port) throws Exception {
     //   System.out.println(JdbcPool.a);
        EventLoopGroup bossGroup = new EpollEventLoopGroup(0x1, Executors.newCachedThreadPool()); //mainReactor    1个线程
        EventLoopGroup workerGroup = new EpollEventLoopGroup(Runtime.getRuntime().availableProcessors() * 0x3, Executors.newCachedThreadPool());   //subReactor       线程数量等价于cpu个数+1
        try {
            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
            b.group(bossGroup, workerGroup).channel(EpollServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
                            ch.pipeline().addLast(new HttpResponseEncoder());
                            // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                            ch.pipeline().addLast(new HttpRequestDecoder());//有两次FIle操作
                            ch.pipeline().addLast(new HttpObjectAggregator(65535));//把上一句的两次File操作聚合在一起
                            ch.pipeline().addLast(new ChunkedWriteHandler());//Chunked是一种报文，处理后返回去，报文回去查一下
                            //  ch.pipeline().addLast(new Handle1());
                            // ch.pipeline().addLast(new CheckHandler());
                            ch.pipeline().addLast( new OutHandler());
                            ch.pipeline().addLast( new InHandler());
                            // 就会有两次File处理（一次头处理，一次体处理）
                        }

                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS,10000)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)     //重用地址
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)// heap buf 's better
                    .childOption(ChannelOption.SO_RCVBUF, 1048576)
                    .childOption(ChannelOption.SO_SNDBUF, 1048576);
            //ChannelFuture f = b.bind(port).sync();
            ChannelFuture f=b.bind("0.0.0.0",port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread inbound=new Thread(new Runnable() {
            @Override
            public void run() {
                server serverIn = new server();
                try {
                    serverIn.startinbound(8006);
                } catch (Exception e) {
                    log.error("Inbound Server crash!!!",e);
                    System.exit(1);
                }
            }
        });
        log.info("aaaa");
        inbound.start();
    }

}
