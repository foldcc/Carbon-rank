package com.carbon_rank.handler;

import com.carbon_rank.data.C_FilterConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

/***
 * 仅允许Http请求访问后续逻辑
 */
public class FilterHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest)
        {
            ctx.pipeline().addLast(new SystemHandler());
            ctx.pipeline().addLast(new UserHandler());
            QueryStringDecoder queryStringDecoder = new QueryStringDecoder(((HttpRequest) msg).uri());
            if (!C_FilterConfig.Request_Type.equals("ALL")){
                if (((HttpRequest) msg).method().name().equals(C_FilterConfig.Request_Type))
                    super.channelRead(ctx, queryStringDecoder);
                else
                    ctx.close();
            }else
                super.channelRead(ctx, queryStringDecoder);
        }else
            ctx.close();
    }
}
