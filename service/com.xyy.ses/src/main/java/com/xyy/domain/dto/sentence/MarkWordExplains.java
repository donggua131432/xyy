package com.xyy.domain.dto.sentence;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "MarkWordExplains",description = "标记单词释意信息")
@Data
public class MarkWordExplains implements Serializable {
    private static final long serialVersionUID = 7066936122778183791L;

    @ApiModelProperty("释意ID")
    private String mweId;
    @ApiModelProperty("单词ID")
    private String mswId;
    @ApiModelProperty("释意")
    private String explains;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}