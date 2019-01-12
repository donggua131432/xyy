package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.SentenceWordRel;

public interface SentenceWordRelMapper {
    int deleteByPrimaryKey(String swlId);

    int insert(SentenceWordRel record);

    int insertSelective(SentenceWordRel record);

    SentenceWordRel selectByPrimaryKey(String swlId);

    int updateByPrimaryKeySelective(SentenceWordRel record);

    int updateByPrimaryKey(SentenceWordRel record);
}