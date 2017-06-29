package com.hw.xyls.service.serviceImpl.image;

import com.hw.xyls.dao.label.ImageRecordMapper;
import com.hw.xyls.dao.label.LabelMapper;
import com.hw.xyls.pojo.label.ImageRecord;
import com.hw.xyls.pojo.label.Label;
import com.hw.xyls.service.image.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/5/23.
 */
@Service
@Scope("prototype")
public class ILabelServiceImpl implements ILabelService {
    @Autowired
    LabelMapper labelMapper;
    @Autowired
    ImageRecordMapper imageRecordMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Map<String, Object> addLabel(Label label, Integer recordid) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (labelMapper.insertSelective(label) < 1) {
                map.put("result", 0);
                throw new RuntimeException("标注出错1");
            }
            ImageRecord imageRecord = imageRecordMapper.selectByPrimaryKey(recordid);
            imageRecord.setRecordid(recordid);
            imageRecord.setCompletednum(imageRecord.getCompletednum() + 1);
            imageRecord.setCurrentimageid(label.getImageid());
            if (imageRecord.getCompletednum() == 9) {
                imageRecord.setIscomplete(1);
                if (imageRecord.getCompleteimageids() == null)
                    imageRecord.setCompleteimageids(label.getImageid().toString());
                else
                    imageRecord.setCompleteimageids(imageRecord.getCompleteimageids() + "," + label.getImageid().toString());
            }
            if (imageRecordMapper.updateByPrimaryKeySelective(imageRecord) > 0) {
                map.put("result", 1);
            } else {
                map.put("result", 0);
                throw new RuntimeException("标注出错2");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", 0);
            throw new RuntimeException("标注出错2");
        } finally {
            return map;
        }
    }


    @Transactional
    @Override
    public Map<String, Object> updateLabel(Label label) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
                if (labelMapper.updateByPrimaryKeySelective(label) < 1) {
                    map.put("result", 0);
                    throw new RuntimeException("标注出错1");
                }
            map.put("result", 1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", 0);
            throw new RuntimeException("标注出错2");
        } finally {
            return map;
        }
    }

    @Override
    public Map<String, Object> deleteLabel(Integer labelid) {
        Label label = new Label();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            label.setImageid(labelid);
            label.setIsdel(1);
            if (labelMapper.updateByPrimaryKeySelective(label) < 1) {
                map.put("result", 0);
                throw new RuntimeException("标注出错1");
            }
            map.put("result", 1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", 0);
            throw new RuntimeException("标注出错2");
        } finally {
            return map;
        }
    }

    @Override
    public List<Label> obtainLabelByUid(Integer uid, Integer imageid) {
        return labelMapper.getLabelListByUid(imageid,uid);
    }
}
