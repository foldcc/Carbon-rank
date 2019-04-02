package com.carbon_rank.handler;

import com.carbon_rank.data.C_FilterConfig;
import com.carbon_rank.data.D_Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.QueryStringDecoder;

/***
 * 系统类逻辑处理
 */
public class SystemHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取请求参数
        if(((QueryStringDecoder)msg).path().equals(C_FilterConfig.SYSTEM_PATH)){
            //TODO SystemAction
            //测试返回
            ctx.writeAndFlush(D_Response.GetResponse(new D_Response(1 , "System: HelloWorld" , null)));
        }
        else
            super.channelRead(ctx, msg);
    }
}
