package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "SentenceWordRel",description = "句子和单词关联关系信息")
@Data
public class SentenceWordRel implements Serializable {
    private static final long serialVersionUID = 977966434721077568L;

    @ApiModelProperty("句子单词关联ID")
    private String swlId;
    @ApiModelProperty("句子ID")
    private String eslId;
    @ApiModelProperty("单词ID")
    private String mswId;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}