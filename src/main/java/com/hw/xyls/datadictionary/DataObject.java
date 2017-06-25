package com.hw.xyls.datadictionary;

import java.util.Date;

/**
 * Created by gaowenfeng on 2017/2/19.
 */
public class DataObject {
    public String content;
    public Date createTime ;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
