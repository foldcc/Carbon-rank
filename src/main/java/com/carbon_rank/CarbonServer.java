package com.carbon_rank;

import com.carbon_rank.bean.user.UserInfoMapper;
import com.carbon_rank.data.C_FilterConfig;
import com.carbon_rank.data.D_SqlSessionFactory_Instance;
import com.carbon_rank.handler.FilterHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class CarbonServer {
    public void Create(int port){
        EventLoopGroup bossGrop = new NioEventLoopGroup();
        EventLoopGroup workGrop = new NioEventLoopGroup();
        //设置handler
        ChannelHandler channelHandler = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
                ch.pipeline().addLast(new HttpResponseEncoder());
                // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                ch.pipeline().addLast(new HttpRequestDecoder());
                ch.pipeline().addLast(new FilterHandler());
            }
        };

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGrop , workGrop)
                .channel(NioServerSocketChannel.class)
                .childHandler(channelHandler)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            System.out.println("========================================");
            System.out.println("system path : " + C_FilterConfig.SYSTEM_PATH);
            System.out.println("user path : " + C_FilterConfig.USER_PATH);
            System.out.println("request type : " + C_FilterConfig.Request_Type);
            System.out.println("server start | bind: " + port);
            System.out.println("========================================");
            //同步阻塞等待
            ChannelFuture channelFuture;
            channelFuture = serverBootstrap.bind(port).sync();
            //关闭服务器
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workGrop.shutdownGracefully();
            bossGrop.shutdownGracefully();
            System.out.println("========================================");
            System.err.println("server close | bind: " + port);
        }
    }

    public  static  void  main(String[] args){
//        com.carbon_rank.CarbonServer server = new com.carbon_rank.CarbonServer();
//        server.Create(8089);
        SqlSession sqlSession = D_SqlSessionFactory_Instance.getSqlSessionFactory().openSession();
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
//        Map<String, Object> userInfo = mapper.getUserInfoById(123456);
//        for (String key : userInfo.keySet()) {
//            System.out.println("\tkey= \t"+ key + "  \tvalue= \t" + userInfo.get(key));
//        }
        List<Object> allUserInfo = mapper.getAllUserInfo();
        for (Object ma:allUserInfo
             ) {
            System.out.println(((Map<String,Object>)ma).toString());
//            for (String key : ma.keySet()) {
//                System.out.println("\tkey= \t"+ key + "  \tvalue= \t" + ma.get(key));
//            }
        }
        System.out.println(allUserInfo.toString());
        sqlSession.close();
    }
}
