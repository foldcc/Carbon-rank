package data;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;

public class D_Response {
    public int state;
    public Object data;
    public String msg;

    public D_Response(int state , Object data , String msg){
        this.state = state;
        this.data = data;
        this.msg = msg;
    }

    public static FullHttpResponse GetResponse(D_Response d_response){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(new Gson().toJson(d_response) , CharsetUtil.UTF_8));
        response.headers().set("Content-Type", "text/html; charset=utf-8");
        return  response;
    }
}
