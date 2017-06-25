package com.hw.xyls.dao.label;

import com.hw.xyls.pojo.image.ComplateImage;
import com.hw.xyls.pojo.label.Result;

import java.util.List;

public interface ResultMapper {
    int deleteByPrimaryKey(Integer resultid);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer resultid);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);

    List<Result> getResultList();

    List<ComplateImage> getComplateImage();

    int doCreateResultBatch(List<Result> list);
}