package handler;

import data.C_FilterConfig;
import data.D_Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.QueryStringDecoder;

/***
 * 逻辑事务处理
 */
public class NormalHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(((QueryStringDecoder)msg).path().equals(C_FilterConfig.NORMAL_NAME)){
            //TODO NormalAction
            //测试返回
            ctx.writeAndFlush(D_Response.GetResponse(new D_Response(1 , "Nromal: HelloWorld" , null)));
        }
        else
            super.channelRead(ctx, msg);
    }
}
