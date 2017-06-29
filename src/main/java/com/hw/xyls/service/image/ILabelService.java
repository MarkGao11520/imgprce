package com.hw.xyls.service.image;

import com.hw.xyls.pojo.label.Label;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/5/23.
 */
public interface ILabelService {
    /**
     * 添加标注
     * @param label
     * @return
     */
    Map<String,Object> addLabel(Label label,Integer recordid);

    /**
     * 编辑标注
     * @param label
     * @return
     */
    Map<String,Object> updateLabel(Label label);


    /**
     * 删除标注
     * @param labelid
     * @return
     */
    Map<String,Object> deleteLabel(Integer labelid);

    /**
     * 根据图片id和用户id或者标注
     * @param uid
     * @param imageid
     * @return
     */
    List<Label> obtainLabelByUid(Integer uid,Integer imageid);

}
