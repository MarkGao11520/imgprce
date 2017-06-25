package com.hw.xyls.service.serviceImpl.image;

import com.hw.xyls.dao.image.ClassesMapper;
import com.hw.xyls.dao.label.ImageRecordMapper;
import com.hw.xyls.pojo.image.Classes;
import com.hw.xyls.pojo.label.ImageRecord;
import com.hw.xyls.service.image.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/5/23.
 */
@Service
@Scope("prototype")
public class IClassServiceImpl implements IClassService{
    @Autowired
    ClassesMapper classesMapper;
    @Autowired
    ImageRecordMapper imageRecordMapper;

    /**
     * 获取没有标注过的图片组
     * @param uid
     * @return
     */
    @Override
    public List<Classes> getUnFinishedGroup(Integer uid) {
        return classesMapper.selectUnFinishedGroup(uid);
    }

    /**
     * 获取正在标注的图片组
     * @param uid
     * @return
     */
    @Override
    public List<Classes> getCurrentImageGroup(Integer uid) {
        return classesMapper.selectCurrentImageGroup(uid);
    }

    /**
     * 获取历史记录
     * @param uid
     * @return
     */
    @Override
    public List<Classes> getHistoryRecord(Integer uid) {
        return classesMapper.selectHistoryRecord(uid);
    }

    /**
     * 添加图片标注记录
     * @param imageRecord
     * @return
     */
    @Override
    public Map<String, Object> addImageRecord(ImageRecord imageRecord) {
        Map<String, Object> map = new HashMap<String,Object>();
        try{
            if(imageRecordMapper.insertSelective(imageRecord)>0){
                map.put("result","1");
                map.put("recordid",imageRecord.getRecordid());
            }else{
                map.put("result","0");
                map.put("recordid","");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("recordid","");
            map.put("result","0");
        }finally {
            return map;
        }
    }
}
