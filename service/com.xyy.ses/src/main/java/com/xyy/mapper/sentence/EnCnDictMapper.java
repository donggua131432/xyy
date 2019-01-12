package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.EnCnDict;

public interface EnCnDictMapper {
    int deleteByPrimaryKey(String ecdId);

    int insert(EnCnDict record);

    int insertSelective(EnCnDict record);

    EnCnDict selectByPrimaryKey(String ecdId);

    int updateByPrimaryKeySelective(EnCnDict record);

    int updateByPrimaryKey(EnCnDict record);
}