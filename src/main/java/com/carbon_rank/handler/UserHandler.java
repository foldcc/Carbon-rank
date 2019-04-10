package com.carbon_rank.handler;

import com.carbon_rank.Tools.requestController;
import com.carbon_rank.data.C_FilterConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.QueryStringDecoder;

/***
 * 逻辑事务处理
 */
public class UserHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(((QueryStringDecoder)msg).path().equals(C_FilterConfig.USER_PATH)){
            requestController.requestMsg(ctx, (QueryStringDecoder) msg);
        }
        else
            super.channelRead(ctx, msg);
    }
}
