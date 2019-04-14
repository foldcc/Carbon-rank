package com.carbon_rank.action;

import com.carbon_rank.Interface.IAction;
import com.carbon_rank.data.D_Response;

import java.util.List;
import java.util.Map;

public class User_selectUserInfo implements IAction {

    @Override
    public D_Response OnClientAction(Map<String, List<String>> params) {
        return new D_Response(1 , "你访问了selectUserInfo" , "user");
    }
}
