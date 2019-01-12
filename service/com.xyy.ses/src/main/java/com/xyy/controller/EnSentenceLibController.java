package com.xyy.controller;

import com.xyy.domain.ReturnInfo;
import com.xyy.domain.dto.sentence.EnSentenceLib;
import com.xyy.domain.vo.req.UserRegister;
import com.xyy.enums.ErrorCodeEnum;
import com.xyy.service.sentence.EnSentenceLibService;
import com.xyy.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述: 英语句子信息接口
 * @作者: DuKai
 * @创建时间: 2019/1/12 23:11
 * @版本号: V1.0
 */
@RestController
@Api(tags={"英语句子信息接口"}, description = "EnSentence-API")
@RequestMapping("/enSentence")
public class EnSentenceLibController {

    @Autowired
    private EnSentenceLibService enSentenceLibService;

    @ApiOperation(value="添加句子信息", notes="添加句子信息")
    @PostMapping("/addEnSentence")
    public ReturnInfo addEnSentence(@ApiParam(name="EnSentenceLib", value="英语句子信息", required=true)
                                        @RequestBody EnSentenceLib enSentenceLib){
        String eslId = StringUtil.UUID();
        enSentenceLib.setEslId(eslId);

        int flag = enSentenceLibService.addEnSentence(enSentenceLib);
        if (flag > 0){
            return ReturnInfo.getInstance().setResult(ErrorCodeEnum.SUCCESS).setData(eslId);
        }
        return ReturnInfo.getInstance().setResult(ErrorCodeEnum.INSERT_FAILED);
    }
}
