package com.hw.xyls.service.image;

import com.hw.xyls.pojo.image.Classes;
import com.hw.xyls.pojo.label.ImageRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/5/23.
 */
public interface IClassService {
    /**
     * 获取没有标注过的图片组
     * @param uid
     * @return
     */
    List<Classes> getUnFinishedGroup(Integer uid);

    /**
     * 获取正在标注的图片组
     * @param uid
     * @return
     */
    List<Classes> getCurrentImageGroup(Integer uid);

    /**
     * 获取历史记录
     * @param uid
     * @return
     */
    List<Classes> getHistoryRecord(Integer uid);

    /**
     * 添加图片标注记录
     * @param imageRecord
     * @return
     */
    Map<String,Object> addImageRecord(ImageRecord imageRecord);
}
