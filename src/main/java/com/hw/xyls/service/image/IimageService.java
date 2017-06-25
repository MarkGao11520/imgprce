package com.hw.xyls.service.image;

import com.github.pagehelper.PageInfo;
import com.hw.xyls.pojo.image.Image;
import com.hw.xyls.pojo.image.ImageDto;
import com.hw.xyls.pojo.label.Label;
import com.hw.xyls.pojo.label.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/5/22.
 */
public interface IimageService {
    /**
     * 图片定时分组
     */
    void picturesScheduledGrouping();

    /**
     * 定时生成标签化结果
     */
    void createResultScheduled();

    /**
     * 根据关键字查找
     * @param key
     * @return
     */
    List<Image> obtainImageByKey(String key);

    /**
     * 批量上传图片
     * @param zip
     * @param uid
     * @return
     */
    Map<String,Object> doUploadImageBatchs(MultipartFile zip, Integer uid);

    /**
     * 获取图片列表
     * @param page
     * @param rows
     * @param imageDto
     * @return
     */
    PageInfo<Image> obtainImageListByDto(int page, int rows, ImageDto imageDto);

     /**
     * 获取图片标注结果列表
     * @param page
     * @param rows
     * @return
     */
    PageInfo<Result> obtainResultList(int page, int rows);


}
