package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.ReadSentenceList;

public interface ReadSentenceListMapper {
    int deleteByPrimaryKey(String rslId);

    int insert(ReadSentenceList record);

    int insertSelective(ReadSentenceList record);

    ReadSentenceList selectByPrimaryKey(String rslId);

    int updateByPrimaryKeySelective(ReadSentenceList record);

    int updateByPrimaryKey(ReadSentenceList record);
}