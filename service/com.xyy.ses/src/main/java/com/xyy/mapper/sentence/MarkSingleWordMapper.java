package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.MarkSingleWord;

public interface MarkSingleWordMapper {

    int deleteMarkSingleWord(String mswId);

    int insertMarkSingleWord(MarkSingleWord markSingleWord);

    MarkSingleWord selectMarkSingleWordById(String mswId);

    int updateMarkSingleWord(MarkSingleWord markSingleWord);

}