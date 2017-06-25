package com.hw.xyls.dao.image;

import com.hw.xyls.pojo.image.Image;
import com.hw.xyls.pojo.image.ImageDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer imageid);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer imageid);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> selectUnGroupedImagesList();

    int picturesGrouping(List<Image> imageList);

    List<Image> selectImageByKey(@Param("key") String key);

    List<Image> selectImagesList(@Param("dto") ImageDto state);

    @Insert("<script> INSERT INTO tb_image(imagePath,upload_time,adminId) VALUES\n" +
            "    <foreach collection=\"list\" item=\"item\" separator=\",\">\n" +
            "      (#{item.imagepath},#{item.uploadTime},#{item.adminid})\n" +
            "    </foreach></script>")
    @Options(useGeneratedKeys = true,keyProperty = "imageid")
    int insertImageBatch(List<Image> list);

    int doImageComplate(List<Integer> list);
}