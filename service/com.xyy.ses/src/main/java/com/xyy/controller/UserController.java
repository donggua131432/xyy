package com.xyy.controller;

import com.github.pagehelper.PageInfo;
import com.xyy.constants.RedisKeyConstants;
import com.xyy.domain.ResultPage;
import com.xyy.domain.ReturnInfo;
import com.xyy.domain.dto.user.User;
import com.xyy.domain.vo.req.UserQuery;
import com.xyy.domain.vo.req.UserRegister;
import com.xyy.domain.vo.res.DynamicSecretKey;
import com.xyy.enums.ErrorCodeEnum;
import com.xyy.redis.RedisService;
import com.xyy.service.user.UserService;
import com.xyy.util.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@Api(tags={"用户信息接口"}, description = "User-API")
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息", notes="获取用户信息")
    @GetMapping("/getUser/{userId}")
    public ReturnInfo<User> getUserById(@ApiParam(value = "用户ID",required = true)@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ReturnInfo.getInstance().setResult(ErrorCodeEnum.SUCCESS).setData(user);
    }

    @ApiOperation(value = "获取分页用户列表", notes = "获取分页用户列表")
    @PostMapping("/listAllUserPage")
    public ReturnInfo<ResultPage<User>> listAllUserPage(@ApiParam("用户查询参数") @RequestBody UserQuery userQuery) {
        PageInfo<User> pageInfo = userService.selectListUserPage(userQuery);
        ResultPage<User> resultPage = ResultPage.getInstance().setPageNum(pageInfo.getPageNum())
                .setPages(pageInfo.getPages())
                .setTotal(pageInfo.getTotal())
                .setList(pageInfo.getList());
        return new ReturnInfo<User>().setResult(ErrorCodeEnum.SUCCESS).setData(resultPage);
    }

    /**
     * 这里已经在 passwordFilter 进行了登录认证
     * 登录签发 JWT
     * @param appId 应用ID
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    public ReturnInfo<User> login(@ApiParam(value="应用ID",required=true) @RequestParam String appId) {
        //根据AppId获取用户信息
        User user = userService.getUserById(appId);
        // 时间以秒计算, token有效时间是 token签发到期时间的2倍
        String jwtToken = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(), user.getUserId(),
                "asc-server", (long) (RedisKeyConstants.JWT_TTL >> 1),
                null, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        RedisService.getRedisService().putObject("JWT-SESSION-" + user.getUserId(), jwtToken, RedisKeyConstants.JWT_TTL);
        user.setPassword(null);
        user.setSalt(null);
        user.setToken(jwtToken);
        return ReturnInfo.getInstance().setResult(ErrorCodeEnum.SUCCESS).setData(user);
    }

    @ApiOperation(value = "用户获取动态秘钥", notes = "用户获取动态秘钥")
    @GetMapping("/getDynamicSecretKey")
    public ReturnInfo<DynamicSecretKey> getDynamicSecretKey(HttpServletRequest request) {
        //动态生成秘钥，redis存储秘钥供之后秘钥验证使用，设置有效期5秒用完即丢弃
        String userKey = StringUtil.randomNumStr(6).toUpperCase();
        String tokenKey = StringUtil.randomNumStr(16);
        try {
            logger.info("TOKEN_KEY_"+ IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase()+userKey);
            RedisService.getRedisService().putObject(
                    "TOKEN_KEY_"+ IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase()+userKey,
                    tokenKey,
                    RedisKeyConstants.TOKEN_TTL);
            //动态秘钥返回给前端
            DynamicSecretKey dynamicSecretKey = new DynamicSecretKey();
            dynamicSecretKey.setUserKey(userKey);
            dynamicSecretKey.setTokenKey(tokenKey);
            return ReturnInfo.getInstance().setResult(ErrorCodeEnum.SUCCESS).setData(dynamicSecretKey);
        }catch (Exception e) {
            return ReturnInfo.getInstance().setResult(ErrorCodeEnum.ISSUE_TOKEN_FAIL);
        }
    }


    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/register")
    public ReturnInfo register(@ApiParam(name = "User",value = "用户注册信息",required = true) @RequestBody UserRegister userRegister) {
        User user = new User();
        user.setUserName(userRegister.getUserName());
        user.setEmail(userRegister.getEmail());
        user.setMobile(userRegister.getMobile());
        user.setPassword(userRegister.getPassword());

        int flag = userService.addUser(user);
        ErrorCodeEnum ee = flag > 0 ? ErrorCodeEnum.SUCCESS:ErrorCodeEnum.FAILED;
        return ReturnInfo.getInstance().setResult(ee).setData(null);
    }

}

