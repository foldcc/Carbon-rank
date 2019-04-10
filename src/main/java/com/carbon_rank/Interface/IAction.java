package com.carbon_rank.Interface;
import com.carbon_rank.data.D_Response;

import java.util.Map;

public interface IAction {
    /***
     * 当客户端发起请求时触发
     * @param params
     * @return
     */
    D_Response OnClientAction(Map<String , Object> params);
}
