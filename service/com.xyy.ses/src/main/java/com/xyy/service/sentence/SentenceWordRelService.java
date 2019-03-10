package com.xyy.service.sentence;

import com.xyy.domain.dto.sentence.SentenceWordRel;
import com.xyy.mapper.sentence.SentenceWordRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述: 句子单词关联服务
 * @作者: DuKai
 * @创建时间: 2019/1/20 12:26
 * @版本号: V1.0
 */
@Service
public class SentenceWordRelService {

    @Autowired
    private SentenceWordRelMapper sentenceWordRelMapper;

    public int addSentenceWordRel(SentenceWordRel sentenceWordRel){
        return sentenceWordRelMapper.insertSentenceWordRel(sentenceWordRel);
    }

}
