package com.hw.xyls.dao.label;

import com.hw.xyls.pojo.label.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelMapper {
    int deleteByPrimaryKey(Integer labelid);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer labelid);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    List<Label> getLabelList();

    List<Label> getLabelListByUid(@Param("imageid") Integer imageid,@Param("uid") Integer uid);
}