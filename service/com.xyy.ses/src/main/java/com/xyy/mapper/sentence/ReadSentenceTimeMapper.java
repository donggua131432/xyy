package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.ReadSentenceTime;

public interface ReadSentenceTimeMapper {
    int deleteByPrimaryKey(String rstId);

    int insert(ReadSentenceTime record);

    int insertSelective(ReadSentenceTime record);

    ReadSentenceTime selectByPrimaryKey(String rstId);

    int updateByPrimaryKeySelective(ReadSentenceTime record);

    int updateByPrimaryKey(ReadSentenceTime record);
}