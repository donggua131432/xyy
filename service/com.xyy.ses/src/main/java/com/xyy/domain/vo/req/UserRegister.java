package com.xyy.domain.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @描述: 用户注册信息
 * @作者: DuKai
 * @创建时间: 2019/1/3 16:32
 * @版本号: V1.0
 */
@ApiModel(value = "UserRegister",description = "用户注册信息")
@Data
public class UserRegister implements Serializable {
    private static final long serialVersionUID = 3421657132936565167L;

    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("手机")
    private String mobile;
}
