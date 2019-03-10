package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.SentenceWordRel;

public interface SentenceWordRelMapper {

    int deleteSentenceWordRelById(String swlId);

    int insertSentenceWordRel(SentenceWordRel sentenceWordRel);

}