package com.carbon_rank.Tools;

import com.carbon_rank.data.D_Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class requestController {
    public static void requestMsg(ChannelHandlerContext ctx, QueryStringDecoder msg) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<String, List<String>> parameters = msg.parameters();
        try {
            Class actionClass = Class.forName("com.carbon_rank.action." + parameters.get("method").get(0));
            Method actionMethod = actionClass.getMethod("OnClientAction" , Map.class);
            ctx.writeAndFlush(D_Response.GetResponse((D_Response) actionMethod.invoke(actionClass.newInstance() , parameters)));
        }catch (ClassNotFoundException e ){
            ctx.writeAndFlush(D_Response.GetErrResponse("1001 ClassNotFound"));
        }catch (NullPointerException e){
            ctx.writeAndFlush(D_Response.GetErrResponse("2000 NullMethod"));
        }catch (NoSuchMethodException e){
            ctx.writeAndFlush(D_Response.GetErrResponse("1002 NoSuchMethod"));
        }
    }
}
