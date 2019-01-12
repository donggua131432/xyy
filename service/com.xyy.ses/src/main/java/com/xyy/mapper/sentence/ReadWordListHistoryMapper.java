package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.ReadWordListHistory;

public interface ReadWordListHistoryMapper {
    int deleteByPrimaryKey(String rwlId);

    int insert(ReadWordListHistory record);

    int insertSelective(ReadWordListHistory record);

    ReadWordListHistory selectByPrimaryKey(String rwlId);

    int updateByPrimaryKeySelective(ReadWordListHistory record);

    int updateByPrimaryKey(ReadWordListHistory record);
}