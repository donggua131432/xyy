package com.xyy.controller;

import com.xyy.domain.ReturnInfo;
import com.xyy.domain.dto.sentence.EnSentenceLib;
import com.xyy.domain.dto.sentence.MarkSingleWord;
import com.xyy.domain.dto.sentence.MarkWordExplains;
import com.xyy.domain.dto.sentence.SentenceWordRel;
import com.xyy.domain.vo.req.EnSentenceReq;
import com.xyy.domain.vo.req.MarkSingleWordReq;
import com.xyy.enums.ErrorCodeEnum;
import com.xyy.service.sentence.EnSentenceLibService;
import com.xyy.service.sentence.MarkSingleWordService;
import com.xyy.service.sentence.MarkWordExplainsService;
import com.xyy.service.sentence.SentenceWordRelService;
import com.xyy.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private SentenceWordRelService sentenceWordRelService;

    @Autowired
    private MarkSingleWordService markSingleWordService;

    @Autowired
    private MarkWordExplainsService markWordExplainsService;

    @ApiOperation(value="添加句子信息", notes="添加句子信息")
    @PostMapping("/addEnSentence")
    @Transactional
    public ReturnInfo addEnSentence(@ApiParam(name="EnSentenceLib", value="英语句子信息", required=true)
                                        @RequestBody EnSentenceReq enSentenceReq){
        //句子ID
        String eslId = StringUtil.UUID();

        //添加标记单词信息
        List<MarkSingleWordReq> markSingleWordReqList = enSentenceReq.getMarkSingleWordReq();
        markSingleWordReqList.forEach(markSingleWordReq -> {
            //句子单词关联ID
            String swlId = StringUtil.UUID();

            //判断单词是否存在单词库中

            //标记单词Id
            String mswId = StringUtil.UUID();

            //句子和单词关联关系
            SentenceWordRel sentenceWordRel = new SentenceWordRel();
            sentenceWordRel.setSwlId(swlId);
            sentenceWordRel.setEslId(eslId);
            sentenceWordRel.setMswId(mswId);
            sentenceWordRelService.addSentenceWordRel(sentenceWordRel);

            //标记单词信息
            MarkSingleWord markSingleWord = new MarkSingleWord();
            markSingleWord.setMswId(mswId);
            markSingleWord.setUserId(enSentenceReq.getUserId());
            markSingleWord.setSingelWord(markSingleWordReq.getSingelWord());
            markSingleWord.setPhonetic(markSingleWordReq.getPhonetic());
            markSingleWord.setConsultStatus("0");
            markSingleWordService.addMarkSingleWord(markSingleWord);

            //判断单词是否存在释义

            //添加释义信息
            List<MarkWordExplains> markWordExplainsList = new ArrayList<>();
            markSingleWordReq.getWordExplainsList().forEach(explains ->{
                String mweId = StringUtil.UUID();
                MarkWordExplains markWordExplains = new MarkWordExplains();
                markWordExplains.setMweId(mweId);
                markWordExplains.setMswId(mswId);
                markWordExplains.setExplains(explains);
                markWordExplainsList.add(markWordExplains);
            });
            if(markWordExplainsList.size()>0){
                markWordExplainsService.addMarkWordExplainsBatch(markWordExplainsList);
            }
        });


        //添加句子信息
        EnSentenceLib enSentenceLib = new EnSentenceLib();
        enSentenceLib.setEslId(eslId);
        enSentenceLib.setUserId(enSentenceReq.getUserId());
        enSentenceLib.setSentenceContent(enSentenceReq.getSentenceContent());
        
        int flag = enSentenceLibService.addEnSentence(enSentenceLib);
        if (flag > 0){
            return ReturnInfo.getInstance().setResult(ErrorCodeEnum.SUCCESS).setData(eslId);
        }
        return ReturnInfo.getInstance().setResult(ErrorCodeEnum.INSERT_FAILED);
    }
}
