package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.EnSentenceLib;

public interface EnSentenceLibMapper {

    int deleteEnSentence(String eslId);

    int insertEnSentence(EnSentenceLib enSentenceLib);

    EnSentenceLib selectEnSentenceById(String eslId);

    int updateEnSentence(EnSentenceLib record);
}