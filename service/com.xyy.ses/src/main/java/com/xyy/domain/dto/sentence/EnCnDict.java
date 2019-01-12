package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "EnCnDict",description = "英汉词典信息")
@Data
public class EnCnDict implements Serializable {
    private static final long serialVersionUID = 9040615707395662372L;

    @ApiModelProperty("英汉词典Id")
    private String ecdId;
    @ApiModelProperty("单词")
    private String singleWord;
    @ApiModelProperty("英式音标")
    private String ukPhonetic;
    @ApiModelProperty("美式音标")
    private String usPhonetic;
    @ApiModelProperty("翻译")
    private String translation;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}