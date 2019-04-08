package com.carbon_rank.mapper.user;
import java.util.List;
import java.util.Map;

public interface UserInfoMapper {

    /***
     * 查询指定id的用户信息
     * @param id
     * @return
     */
    Map<String,Object> getUserInfoById(Integer id);

    /***
     * 查询所有用户信息
     * @return
     */
    List<Map<String,Object>> getAllUserInfo();
}
