package com.xyy.domain.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @描述: 用户查询实体
 * @作者: DuKai
 * @创建时间: 2018/12/6 17:28
 * @版本号: V1.0
 */

@ApiModel(value = "UserQuery",description = "用户查询实体")
@Data
public class UserQuery extends PageCondition implements Serializable {
    private static final long serialVersionUID = 7592285921834808615L;

    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("手机")
    private String mobile;
}
