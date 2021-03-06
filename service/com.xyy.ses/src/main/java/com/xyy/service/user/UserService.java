package com.xyy.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyy.domain.Account;
import com.xyy.domain.dto.user.User;
import com.xyy.domain.vo.req.UserQuery;
import com.xyy.mapper.user.UserMapper;
import com.xyy.util.MD5Util;
import com.xyy.util.StringUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述: 用户信息服务层
 * @作者: DuKai
 * @创建时间: 2018/11/20 14:18
 * @版本号: V1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Account loadAccount(String appId) {
        User user = userMapper.selectUserById(appId);
        if (user != null) {
            return new Account(user.getUserName(), user.getPassword(), user.getSalt());
        }
        return null;
    }


    public User getUserById(String userId){
        return userMapper.selectUserById(userId);
    }

    public PageInfo<User> selectListUserPage(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPage(),userQuery.getSize());
        List<User> userList = userMapper.selectListUserPage(userQuery);
        return new PageInfo<>(userList);
    }

    public int addUser(User user) {
        String salt = StringUtil.randomNumStr(8);
        //String password = MD5Util.md5("gy123456"+salt);
        String password = new SimpleHash("MD5",
                user.getPassword(), ByteSource.Util.bytes(salt),5).toHex();
        user.setSalt(salt);
        user.setPassword(password);
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int deleteUser(String userId) {
        return userMapper.deleteUserById(userId);
    }

    public int reSetPassword(String userId) {
        String salt = StringUtil.randomNumStr(8);
        String password = new SimpleHash("MD5",
                "xyy123456", ByteSource.Util.bytes(salt),5).toHex();
        return userMapper.reSetPassword(userId,password,salt);
    }

    public static void main(String[] args) {
        String password = new SimpleHash("MD5",
                "xyy123456", ByteSource.Util.bytes("l14GQyKI"),5).toHex();
        System.out.println(password);

        List list1 =new ArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List tempList = new ArrayList();
        tempList.addAll(list1);

        List list2 =new ArrayList();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        tempList.retainAll(list2);

        list1.removeAll(tempList);
        list2.removeAll(tempList);

        list1.stream().forEach(str -> {
            System.out.println(str);
        });

        list2.stream().forEach(str -> {
            System.out.println(str);
        });
    }
}
