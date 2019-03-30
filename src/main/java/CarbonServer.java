import handler.FilterHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

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
            System.out.println("Server Start | bind: " + port);
            //同步阻塞等待
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            //关闭服务器
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workGrop.shutdownGracefully();
            bossGrop.shutdownGracefully();
        }
        System.out.println("Server Close | bind: " + port);
    }
    public  static  void  main(String[] args){
        CarbonServer server = new CarbonServer();
        server.Create(8089);
    }
}
