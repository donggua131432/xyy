package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "ReadWordList",description = "播单学习时间信息")
@Data
public class ReadWordList implements Serializable {
    private static final long serialVersionUID = 1003541960073916638L;

    @ApiModelProperty("播单单词ID")
    private String rwlId;
    @ApiModelProperty("播单ID")
    private String rslId;
    @ApiModelProperty("句子ID")
    private String eslId;
    @ApiModelProperty("单词ID")
    private String mswId;
    @ApiModelProperty("单词")
    private String singelWord;
    @ApiModelProperty("单词开始位置")
    private Integer start;
    @ApiModelProperty("单词结束位置")
    private Integer end;
    @ApiModelProperty("是否生词【0:是 1:否】")
    private String strange;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}