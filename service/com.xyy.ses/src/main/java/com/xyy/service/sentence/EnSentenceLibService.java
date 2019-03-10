package com.xyy.service.sentence;

import com.xyy.domain.dto.sentence.EnSentenceLib;
import com.xyy.mapper.sentence.EnSentenceLibMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述: 英语句子库服务
 * @作者: DuKai
 * @创建时间: 2019/1/12 23:13
 * @版本号: V1.0
 */
@Service
public class EnSentenceLibService {

    @Autowired
    private EnSentenceLibMapper enSentenceLibMapper;

    /**
     * 添加英语句子信息
     * @param enSentenceLib
     * @return
     */
    public int addEnSentence(EnSentenceLib enSentenceLib){
        return enSentenceLibMapper.insertEnSentence(enSentenceLib);
    }
}
