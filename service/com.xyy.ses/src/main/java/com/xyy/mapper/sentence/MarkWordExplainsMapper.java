package com.xyy.mapper.sentence;

import com.xyy.domain.dto.sentence.MarkWordExplains;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarkWordExplainsMapper {

    MarkWordExplains selectMarkWordExplainsById(String mweId);

    int deleteMarkWordExplains(String mweId);

    int insertMarkWordExplainsBatch(@Param("markWordExplains") List<MarkWordExplains> markWordExplains);



}