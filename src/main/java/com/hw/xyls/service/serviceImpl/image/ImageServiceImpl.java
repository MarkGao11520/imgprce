package com.hw.xyls.service.serviceImpl.image;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hw.xyls.dao.image.ClassesMapper;
import com.hw.xyls.dao.image.ImageMapper;
import com.hw.xyls.dao.label.ResultMapper;
import com.hw.xyls.pojo.image.Classes;
import com.hw.xyls.pojo.image.ComplateImage;
import com.hw.xyls.pojo.image.Image;
import com.hw.xyls.pojo.image.ImageDto;
import com.hw.xyls.pojo.label.ComplateLabel;
import com.hw.xyls.pojo.label.Result;
import com.hw.xyls.pojo.user.User;
import com.hw.xyls.service.image.IimageService;
import com.hw.xyls.tool.Tools;
import com.hw.xyls.tool.ZipUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/5/22.
 */
@Service
@Scope("prototype")
public class ImageServiceImpl implements IimageService {
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    ClassesMapper classesMapper;
    @Autowired
    HttpServletRequest request;
    @Autowired
    ResultMapper resultMapper;
    Logger logger = Logger.getLogger(ImageServiceImpl.class);

    /**
     * 图片计划分组
     */
    @Override
    @Scheduled(fixedRate = 120000)
    @Transactional(rollbackFor = RuntimeException.class)
    public void picturesScheduledGrouping() {
        logger.info("图片分组计划任务开始");
        try {
            List<Image> unGroupedImageLists = imageMapper.selectUnGroupedImagesList();
            if (unGroupedImageLists.size() > 0 && unGroupedImageLists != null) {
                logger.info("初始数据:"+JSON.toJSONString(unGroupedImageLists));
                int unGroupedNum = unGroupedImageLists.size();
                List<Classes> groupList = createNewGroupList(unGroupedNum);
                picturesGrouping(unGroupedImageLists, groupList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("图片分组出错3");
        } finally {
            logger.info("图片分组计划任务结束");
        }

    }

    /**
     * 定时生成标签化结果
     */
    @Override
    @Scheduled(fixedRate = 120000)
    @Transactional
    public void createResultScheduled() {
        logger.info("生成图片标签结果计划任务开始");
        List<ComplateImage> complateImages = null;
        try{
            complateImages = resultMapper.getComplateImage();
        }catch (Exception e){
        }
        if(complateImages!=null&&complateImages.size()>0) {
            List<Result> results = new ArrayList<>();
            List<Integer> imageids = new ArrayList<>();
            DecimalFormat df = new DecimalFormat("######0.000");
            for (ComplateImage complateImage : complateImages) {
                StringBuilder labelNameAll = null;
                StringBuilder rateAll = null;
                Double rateNum = 0.00;
                for (ComplateLabel complateLabel : complateImage.getLabel()) {
                    if (labelNameAll == null) {
                        labelNameAll = new StringBuilder();
                        labelNameAll.append(complateLabel.getLabelName());
                    } else
                        labelNameAll.append(",").append(complateLabel.getLabelName());
                    if (rateAll == null) {
                        rateAll = new StringBuilder();
                        rateAll.append(df.format((double) complateLabel.getNum() / (double) complateImage.getCount()));
                    } else
                        rateAll.append(",").append(df.format((double) complateLabel.getNum() / (double) complateImage.getCount()));

                    rateNum += (double) complateLabel.getNum() / (double) complateImage.getCount();
                }
                Result result = new Result();
                result.setImageid(complateImage.getImageId());
                result.setLabelnameAll(labelNameAll.toString());
                result.setRateAll(rateAll.toString());
                result.setCreateTime(new Date().getTime());
                result.setRate(Double.parseDouble(df.format(rateNum / 6)));
                results.add(result);
                imageids.add(complateImage.getImageId());
            }

            if (resultMapper.doCreateResultBatch(results) == results.size()&&imageMapper.doImageComplate(imageids)==results.size()) {
                logger.info("生成图片标签结果成功");
            } else {
                logger.info("生成图片标签结果失败");
                throw new RuntimeException("生成图片标签结果失败");
            }
        }
    }

    /**
     * 根据关键字查找
     *
     * @param key
     * @return
     */
    @Override
    public List<Image> obtainImageByKey(String key) {
        key = "%" + key + "%";
        return imageMapper.selectImageByKey(key);
    }

    @Override
    @Transactional
    public Map<String, Object> doUploadImageBatchs(MultipartFile zip, Integer uid) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Image> imageList = new ArrayList<>();
        List<String> urlList = ZipUtil.decompressing(request, "image", zip);
        if(urlList!=null&&urlList.size()>8) {
            for (String url : urlList) {
                Image image1 = new Image();
                image1.setAdminid(uid);
                image1.setUploadTime(new Date().getTime());
                image1.setImagepath(url);
                imageList.add(image1);
            }
            if (imageMapper.insertImageBatch(imageList) == imageList.size()) {
                resultMap.put("result", 1);
            } else {
                throw new RuntimeException("批量上传图片错误");
            }
        }else {
            resultMap.put("result", -1);
        }
        return resultMap;
    }

    @Override
    public PageInfo<Image> obtainImageListByDto(int page, int rows, ImageDto imageDto) {
        PageHelper.startPage(page, rows);
        List<Image> imageList = imageMapper.selectImagesList(imageDto);
        if (resultHandler(imageList)) {
            return new PageInfo<Image>(imageList);
        } else {
            return new PageInfo<Image>(Collections.emptyList());
        }
    }

    @Override
    public PageInfo<Result> obtainResultList(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Result> resultList = resultMapper.getResultList();
        if (resultList.size() != 0 && resultList != null) {
            for(Result result:resultList){
                if(result.getCreateTime()!=null)
                    result.setCreate(Tools.dataLongToString(result.getCreateTime()));
            }
            return new PageInfo<Result>(resultList);
        } else {
            return new PageInfo<Result>(Collections.emptyList());
        }
    }

    /**
     * 创建新的分组列表并插入到数据库
     *
     * @param unGroupedNum
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    private List<Classes> createNewGroupList(int unGroupedNum) {
        try {
            List<Classes> list = new ArrayList<Classes>();
            int groupNum = unGroupedNum / 9 + 1;
            int remainder = unGroupedNum % 9;
            for (int i = 0; i < groupNum; i++) {
                if (i == groupNum - 1 && remainder > 0) {
                    list.add(createNewGroup(remainder));
                } else if (i < groupNum - 1) {
                    list.add(createNewGroup(9));
                }
            }
//            logger.info("创建前："+JSON.toJSONString(list));
            int addNum = classesMapper.createNewGroups(list);
            if (list.size() == addNum) {
                for(Classes classes:list){
                    classes.setClassname(createCodeName(classes.getClassid()));
                    classesMapper.updateByPrimaryKeySelective(classes);
                }
//                logger.info("创建后："+JSON.toJSONString(list));
                return list;
            } else {
                throw new RuntimeException("创建分组出错1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("创建分组出错2");
        }


    }

    /**
     * 创建新的分组
     *
     * @param imageNums
     * @return
     */
    private Classes createNewGroup(int imageNums) {
        Classes classes = new Classes();
        classes.setImagenums(imageNums);
        return classes;
    }

    private String createCodeName(Integer id){
        Calendar calendar = Calendar.getInstance();
        String code = id.toString().length()==4?id.toString():id.toString().length()==3?"0"+id:id.toString().length()==2?"00"+id:"000"+id;
        StringBuffer sb = new StringBuffer();
        sb.append("XYLS").append(calendar.getWeekYear()).append(code);
        return sb.toString();
    }

    /**
     * 给图片添加分组
     *
     * @param imageList
     * @param classesList
     */
    @Transactional(rollbackFor = RuntimeException.class)
    private void picturesGrouping(List<Image> imageList, List<Classes> classesList) {
        try {
            for (int i = 0; i < imageList.size(); i++) {
                int index;
                if (i / 9 < 1) {
                    index = 0;
                } else {
                    index = i / 9;
                }
                imageList.get(i).setClassid(classesList.get(index).getClassid());
            }
//            System.out.println("更新前："+JSON.toJSONString(imageList));
            int result = imageMapper.picturesGrouping(imageList);
//            System.out.println(result);
            if (result <= 0) {
                throw new RuntimeException("图片分组出错1");
            }
//            System.out.println("更新后："+JSON.toJSONString(imageList));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("图片分组出错2");
        }

    }

    private boolean resultHandler(List<Image> images) {
        if (images == null || images.size() == 0) {
            return false;
        } else {
            for (Image image : images) {
                if (image.getUploadTime() != null)
                    image.setUpload(Tools.dataLongToString(image.getUploadTime()));
            }
            return true;
        }
    }
}
