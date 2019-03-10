package com.xyy.service.sentence;

import com.xyy.domain.dto.sentence.MarkSingleWord;
import com.xyy.mapper.sentence.MarkSingleWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述: 标记单词服务
 * @作者: DuKai
 * @创建时间: 2019/1/20 12:39
 * @版本号: V1.0
 */
@Service
public class MarkSingleWordService {

    @Autowired
    private MarkSingleWordMapper markSingleWordMapper;

    public int addMarkSingleWord(MarkSingleWord markSingleWord){
        return markSingleWordMapper.insertMarkSingleWord(markSingleWord);
    }
}
