package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.ReadSentenceRel;

public interface ReadSentenceRelMapper {
    int deleteByPrimaryKey(String rsrId);

    int insert(ReadSentenceRel record);

    int insertSelective(ReadSentenceRel record);

    ReadSentenceRel selectByPrimaryKey(String rsrId);

    int updateByPrimaryKeySelective(ReadSentenceRel record);

    int updateByPrimaryKey(ReadSentenceRel record);
}