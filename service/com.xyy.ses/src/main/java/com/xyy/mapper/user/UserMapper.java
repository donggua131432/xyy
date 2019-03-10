package com.xyy.mapper.user;

import com.xyy.domain.dto.user.User;
import com.xyy.domain.vo.req.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int deleteUserById(String userId);

    int insertUser(User user);

    User selectUserById(@Param("appId") String appId);

    List<User> selectListUserPage(UserQuery userQuery);

    int updateUser(User user);

    int reSetPassword(@Param("userId") String userId, @Param("password") String password, @Param("slat") String slat);

}