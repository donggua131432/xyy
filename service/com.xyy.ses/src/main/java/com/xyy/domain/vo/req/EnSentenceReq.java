package com.xyy.domain.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "EnSentenceReq",description = "英语句子信息")
@Data
public class EnSentenceReq implements Serializable {
    private static final long serialVersionUID = -5799530468934881882L;

    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "句子内容")
    private String sentenceContent;
    @ApiModelProperty(value = "标记单词信息")
    private List<MarkSingleWordReq> markSingleWordReq;


}