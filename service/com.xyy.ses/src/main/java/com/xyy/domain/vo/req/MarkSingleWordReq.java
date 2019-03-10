package com.xyy.domain.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "MarkSingleWord",description = "标记单词信息")
@Data
public class MarkSingleWordReq implements Serializable {
    private static final long serialVersionUID = 927956503869053855L;

    @ApiModelProperty("单词")
    private String singelWord;
    @ApiModelProperty("音标")
    private String phonetic;
    @ApiModelProperty("标记单词释义集合")
    private List<String> wordExplainsList;

}