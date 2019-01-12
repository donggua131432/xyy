package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "ReadSentenceList",description = "句子播单信息")
@Data
public class ReadSentenceList implements Serializable {
    private static final long serialVersionUID = -4333149925272492235L;

    @ApiModelProperty("句子播单ID")
    private String rslId;
    @ApiModelProperty("播单名称")
    private String rslName;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("完成率")
    private Integer finishRate;
    @ApiModelProperty("生词率")
    private Integer strangeRate;
    @ApiModelProperty("播单状态【0;待学习 1:正在学习 2:曾经学习】")
    private String rslStatus;
    @ApiModelProperty("播单排序类型【1:按时间排序 2:乱序】")
    private String sortType;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}