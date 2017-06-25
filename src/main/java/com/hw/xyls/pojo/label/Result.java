package com.hw.xyls.pojo.label;

import com.hw.xyls.pojo.image.Image;

public class Result {
    private Integer resultid;

    private Integer imageid;

    private String labelnameAll;

    private String rateAll;

    private Double rate;

    private String create;

    private Long createTime;

    private Long updateTime;

    private Image image;

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getResultid() {
        return resultid;
    }

    public void setResultid(Integer resultid) {
        this.resultid = resultid;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getLabelnameAll() {
        return labelnameAll;
    }

    public void setLabelnameAll(String labelnameAll) {
        this.labelnameAll = labelnameAll == null ? null : labelnameAll.trim();
    }

    public String getRateAll() {
        return rateAll;
    }

    public void setRateAll(String rateAll) {
        this.rateAll = rateAll == null ? null : rateAll.trim();
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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
}