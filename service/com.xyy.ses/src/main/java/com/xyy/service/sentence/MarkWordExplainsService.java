package com.xyy.service.sentence;

import com.xyy.domain.dto.sentence.MarkWordExplains;
import com.xyy.mapper.sentence.MarkWordExplainsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述: 单词释义服务
 * @作者: DuKai
 * @创建时间: 2019/1/20 12:54
 * @版本号: V1.0
 */
@Service
public class MarkWordExplainsService {

    @Autowired
    private MarkWordExplainsMapper markWordExplainsMapper;

    /**
     * 批量添加单词释义
     * @param markWordExplains
     * @return
     */
    public int addMarkWordExplainsBatch(List<MarkWordExplains> markWordExplains){
        return markWordExplainsMapper.insertMarkWordExplainsBatch(markWordExplains);
    }
}
