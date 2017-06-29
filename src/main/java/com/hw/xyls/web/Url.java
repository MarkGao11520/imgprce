package com.hw.xyls.web;

/**
 * Created by gaowenfeng on 2017/4/26.
 */
public class Url {
    public static final String IP = "";
    public static final String PORT = "";
    public static final String NAME = "";
    /**
     * 根据发送短信验证码
     */
    public static final String REGISTER_SEND_URL = "androidController/sendMobileCode";
    /**
     * 注册
     */
    public static final String REGISTER_CHECK_URL = "androidController/register";
    /**
     * 修改密码发送手机验证码
     */
    public static final String UPDATEPASSWORD_SEND_URL = "androidController/sendMobileCode";
    /**
     * 修改密码验证
     */
    public static final String UPDATEPASSWORD_CHECK_URL = "androidController/updatePasswordCheck";
    /**
     * 修改密码
     */
    public static final String UPDATEPASSWORD_URL = "androidController/updatePassword";
    /**
     * 登录
     */
    public static final String LOGIN_URL = "androidController/login";
    /**
     * 更新个人信息，刚注册
     */
    public static final String UPDATE_FIRST_URL = "androidController/updateUserByUid";
    /**
     * 更新个人信息（app内）
     */
    public static final String UPDATE_PERSON_INFORMATION_URL = "androidController/updateUserByUid";
    /**
     * 修改头像
     */
    public static final String UPLOAD_AVATOR_URL = "androidController/updateHeadPicByUid";
    /**
     * 修改手机号（无原手机号）验证
     */
    public static final String UPDATE_PHONE_URL = "androidController/updatePhoneByUid";
    /**
     * 获取当前用户正在标注的图片组
     */
    public static final String OBTIAN_CURRENT_IMAGECLASS_URL = "androidController/getCurrentImageGroup";
    /**
     * 获取没有标注过的图片组
     */
    public static final String OBTAIN_UNFINISHED_IMAGECLASSES_URL = "androidController/getUnFinishedGroup";
    /**
     * 图片标注提交
     */
    public static final String IMAGE_SUBMIT_URL = "androidController/imageSubmit";
    /**
     * 修改图片标注
     */
    public static final String UPDATE_LABEL_URL = "androidController/updateLabe";
    /**
     * 删除图片标注
     */
    public static final String DELETE_LABEL_URL = "androidController/deleteLabel";
    /**
     * 获取图片标注
     */
    public static final String GET_LABELLIST_BYUID_ANDIMAGEID_URL = "androidController/getLabelListByUidAndImageId";
    /**
     * 添加图片标注记录
     */
    public static final String ADD_IMAGE_RECORD_URL = "androidController/addImageRecord";
    /**
     * 获取历史记录
     */
    public static final String OBTAIN_HISTORICAL_RECORD_URL = "androidController/getHistoryRecord";
    /**
     * 搜索图片
     */
    public static final String OBTAIN_IMAGECLASSBYKEY_URL = "androidController/getImageByKey";
    /**
     * 添加管理员
     */
    public static final String ADD_ADMIN_URL = "adminController/addAdmin";
    /**
     * 获取管理员列表
     */
    public static final String GET_ADMIN_LIST_URL = "adminController/getAdminList";
    /**
     * 获取图片列表
     */
    public static final String GET_IMAGE_LIST_URL = "adminController/getImageList";
    /**
     * 获取图片标注结果列表
     */
    public static final String GET_RESULT_LIST_URL = "adminController/getResultList";
    /**
     * 批量上传图片
     */
    public static final String UPLOAD_IMAGE_BATCH_URL = "adminController/uploadImageBatch";




}
