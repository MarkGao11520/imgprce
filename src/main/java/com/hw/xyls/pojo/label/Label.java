package com.hw.xyls.pojo.label;

import com.hw.xyls.pojo.image.Image;

public class Label {
    private Integer labelid;

    private String lablename;

    private Integer imageid;

    private Image image;

    private Integer uid;

    private Long createTime;

    private Long updateTime;

    private Integer isdel;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getLabelid() {
        return labelid;
    }

    public void setLabelid(Integer labelid) {
        this.labelid = labelid;
    }

    public String getLablename() {
        return lablename;
    }

    public void setLablename(String lablename) {
        this.lablename = lablename == null ? null : lablename.trim();
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}