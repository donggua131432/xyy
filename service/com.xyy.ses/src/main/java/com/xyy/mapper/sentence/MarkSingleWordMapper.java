package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.MarkSingleWord;

public interface MarkSingleWordMapper {
    int deleteByPrimaryKey(String mswId);

    int insert(MarkSingleWord record);

    int insertSelective(MarkSingleWord record);

    MarkSingleWord selectByPrimaryKey(String mswId);

    int updateByPrimaryKeySelective(MarkSingleWord record);

    int updateByPrimaryKey(MarkSingleWord record);
}