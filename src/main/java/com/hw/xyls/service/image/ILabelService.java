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
    Map<String,Object> addLabel(List<Label> label,Integer recordid);

    /**
     * 编辑标注
     * @param label
     * @return
     */
    Map<String,Object> updateLabel(List<Label> label);

}
