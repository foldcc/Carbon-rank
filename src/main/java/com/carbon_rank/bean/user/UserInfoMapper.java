package com.carbon_rank.bean.user;
import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    Map<String,Object> getUserInfoById(Integer id);
    List<Object> getAllUserInfo();
}
