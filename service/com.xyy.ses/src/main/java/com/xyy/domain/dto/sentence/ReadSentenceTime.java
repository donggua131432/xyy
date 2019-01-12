package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "ReadSentenceTime",description = "播单学习时间信息")
@Data
public class ReadSentenceTime implements Serializable {
    private static final long serialVersionUID = 2689900892426851797L;

    @ApiModelProperty("播单学习时间ID")
    private String rstId;
    @ApiModelProperty("句子播单ID")
    private String rslId;
    @ApiModelProperty("阅读开始时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty("阅读结束时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}