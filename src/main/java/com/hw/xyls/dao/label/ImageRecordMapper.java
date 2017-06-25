package com.hw.xyls.dao.label;

import com.hw.xyls.pojo.label.ImageRecord;

public interface ImageRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(ImageRecord record);

    int insertSelective(ImageRecord record);

    ImageRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(ImageRecord record);

    int updateByPrimaryKey(ImageRecord record);
}