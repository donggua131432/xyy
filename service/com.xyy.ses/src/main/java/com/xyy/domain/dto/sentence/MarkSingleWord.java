package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "MarkSingleWord",description = "标记单词信息")
@Data
public class MarkSingleWord implements Serializable {
    private static final long serialVersionUID = 927956503869053855L;

    @ApiModelProperty("标记单词Id")
    private String mswId;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("单词")
    private String singelWord;
    @ApiModelProperty("音标")
    private String phonetic;
    @ApiModelProperty("是否查阅【0:未查阅  1:已查阅】")
    private String consultStatus;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("修改时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}