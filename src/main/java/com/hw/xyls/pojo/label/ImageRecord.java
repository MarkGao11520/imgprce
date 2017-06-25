package com.hw.xyls.pojo.label;

public class ImageRecord {
    private Integer recordid;

    private Integer classid;

    private Integer userid;

    private Integer completednum;

    private Integer currentimageid;

    private Integer iscomplete;

    private String completeimageids;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCompletednum() {
        return completednum;
    }

    public void setCompletednum(Integer completednum) {
        this.completednum = completednum;
    }

    public Integer getCurrentimageid() {
        return currentimageid;
    }

    public void setCurrentimageid(Integer currentimageid) {
        this.currentimageid = currentimageid;
    }

    public Integer getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(Integer iscomplete) {
        this.iscomplete = iscomplete;
    }

    public String getCompleteimageids() {
        return completeimageids;
    }

    public void setCompleteimageids(String completeimageids) {
        this.completeimageids = completeimageids == null ? null : completeimageids.trim();
    }
}