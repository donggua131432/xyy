package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.MarkWordExplains;

public interface MarkWordExplainsMapper {
    int deleteByPrimaryKey(String mweId);

    int insert(MarkWordExplains record);

    int insertSelective(MarkWordExplains record);

    MarkWordExplains selectByPrimaryKey(String mweId);

    int updateByPrimaryKeySelective(MarkWordExplains record);

    int updateByPrimaryKey(MarkWordExplains record);
}