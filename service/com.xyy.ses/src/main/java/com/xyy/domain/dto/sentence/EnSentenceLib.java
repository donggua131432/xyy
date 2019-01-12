package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "EnSentenceLib",description = "英语句子信息")
@Data
public class EnSentenceLib implements Serializable {
    private static final long serialVersionUID = -5799530468934881882L;

    @ApiModelProperty("句子ID")
    private String eslId;
    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;
    @ApiModelProperty(value = "句子内容", required = true)
    private String sentenceContent;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}