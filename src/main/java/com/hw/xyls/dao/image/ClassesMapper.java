package com.hw.xyls.dao.image;

import com.hw.xyls.pojo.image.Classes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesMapper {
    int deleteByPrimaryKey(Integer classid);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    int createNewGroups(List<Classes> classesList);

    /**
     * 获取没有标注过的图片组
     * @param uid
     * @return
     */
    List<Classes> selectUnFinishedGroup(@Param("uid") Integer uid);

    /**
     * 获取正在标注的图片组
     * @param uid
     * @return
     */
    List<Classes> selectCurrentImageGroup(@Param("uid") Integer uid);

    /**
     * 获取历史记录
     * @param uid
     * @return
     */
    List<Classes> selectHistoryRecord(@Param("uid") Integer uid);

}