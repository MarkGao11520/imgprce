package com.hw.xyls.pojo.image;

import com.hw.xyls.pojo.label.ComplateLabel;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/25.
 */
public class ComplateImage {
    private Integer imageId;
    private Integer count;
    private List<ComplateLabel> label;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public List<ComplateLabel> getLabel() {
        return label;
    }

    public void setLabel(List<ComplateLabel> label) {
        this.label = label;
    }
}
