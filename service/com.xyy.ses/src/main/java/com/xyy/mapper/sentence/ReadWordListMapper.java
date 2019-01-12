package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.ReadWordList;

public interface ReadWordListMapper {
    int deleteByPrimaryKey(String rwlId);

    int insert(ReadWordList record);

    int insertSelective(ReadWordList record);

    ReadWordList selectByPrimaryKey(String rwlId);

    int updateByPrimaryKeySelective(ReadWordList record);

    int updateByPrimaryKey(ReadWordList record);
}