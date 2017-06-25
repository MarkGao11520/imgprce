package com.hw.xyls.pojo.image;

import java.util.List;

public class Classes {
    private Integer classid;

    private String classname;

    private Integer imagenums;

    private List<Image> imageList;

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Integer getImagenums() {
        return imagenums;
    }

    public void setImagenums(Integer imagenums) {
        this.imagenums = imagenums;
    }
}