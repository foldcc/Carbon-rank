package com.carbon_rank.data;

import com.google.gson.Gson;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;


public class D_Response {
    public int flag;
    public Object data;
    public String msg;

    public D_Response(int flag , Object data , String msg){
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }

    public static FullHttpResponse GetResponse(D_Response d_response){
        if (d_response == null){
            return  GetErrResponse("1000");
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(new Gson().toJson(d_response) , CharsetUtil.UTF_8));
        response.headers().set("Content-Type", "text/html; charset=utf-8");
        return  response;
    }

    public static FullHttpResponse GetErrResponse(String errCode){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(new Gson().toJson(new D_Response(0 , null , "ERRO:" + errCode)) , CharsetUtil.UTF_8));
        response.headers().set("Content-Type", "text/html; charset=utf-8");
        return  response;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
