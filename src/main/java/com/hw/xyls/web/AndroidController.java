package com.hw.xyls.web;

import com.hw.xyls.datadictionary.DataDictionary;
import com.hw.xyls.pojo.image.Classes;
import com.hw.xyls.pojo.image.Image;
import com.hw.xyls.pojo.label.ImageRecord;
import com.hw.xyls.pojo.label.Label;
import com.hw.xyls.pojo.user.Login;
import com.hw.xyls.pojo.user.User;
import com.hw.xyls.service.image.IClassService;
import com.hw.xyls.service.image.ILabelService;
import com.hw.xyls.service.image.IimageService;
import com.hw.xyls.service.user.IUserService;
import com.hw.xyls.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/5/18.
 */
@RestController
@Scope("prototype")
@RequestMapping("androidController")
public class AndroidController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IClassService iClassService;
    @Autowired
    ILabelService iLabelService;
    @Autowired
    IimageService iimageService;

    /**
     * 发送手机验证码 已测试
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "sendMobileCode", produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> sendMobileCode(String phone, Integer code) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean result = iUserService.isExists(phone);
        System.out.println(result);
        if(code!=null&&code==1){
            result = result?false:true;
        }
        System.out.println(result);
        if(!result){
            map = Tools.sendMobileCode(phone);
        }else{
            map.put("id","");
            map.put("isSuccess","0");
        }
        return map;
    }

    /**
     * 注册 已测试
     * @param msgcode
     * @param msgid
     * @param login
     * @return
     */
    @RequestMapping(value = "register", produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> register(String msgcode, String msgid, Login login) {
        Map<String,Object> map = new HashMap<String,Object>();
        boolean temp = false;
        try {
            temp = msgcode.equals(DataDictionary.DataMap.get(msgid).getContent());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (temp) {
                try{
                    return iUserService.register(login,new User());
                }catch (Exception e){
                    e.printStackTrace();
                    map.put("user","");
                    map.put("login","");
                    map.put("result",-1);
                    return map;
                }
            }else{
                map.put("user","");
                map.put("login","");
                map.put("result",-2);
                return map;
            }
        }
    }

    /**
     * 更改密码验证手机号 已测试
     * @param msgcode
     * @param id
     * @return
     */
    @RequestMapping(value = "updatePasswordCheck", produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> updatePasswordCheck(String msgcode, String id){
        boolean temp = false;
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            temp = msgcode.equals(DataDictionary.DataMap.get(id).getContent());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (temp) {
                map.put("result","1");
            }else{
                map.put("result","-1");
            }
            return map;
        }
    }

    /**
     * 修改密码 已测试
     * @param login
     * @return
     */
    @RequestMapping(value = "updatePassword", produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> updatePassword(Login login){
        return iUserService.updatePassword(login);
    }

    /**
     * 登录 已测试
     * @param login
     * @return
     */
    @RequestMapping(value = "login", produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> login(Login login){
        return iUserService.login(login);
    }

    /**
     * 更改用户信息 已测试
     * @param user
     * @return
     */
    @RequestMapping(value = "updateUserByUid", produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> updateUserByUid(User user){
        return iUserService.updateUserByUid(user);
    }

    /**
     * 更改手机号 已测试
     * @param msgcode
     * @param msgid
     * @param login
     * @return
     */
    @RequestMapping(value = "updatePhoneByUid", produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> updatePhoneByUid(String msgcode, String msgid, Login login){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean temp = false;
        try {
            temp = msgcode.equals(DataDictionary.DataMap.get(msgid).getContent());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (temp) {
                try{
                    return iUserService.updatePhoneByUid(login);
                }catch (Exception e){
                    e.printStackTrace();
                    map.put("result",-1);
                    return map;
                }
            }else{
                map.put("result",-2);
                return map;
            }
        }
    }

    /**
     * 更改头像 未测试
     * @param user
     * @param httpServletRequest
     * @param file
     * @return
     */
    @RequestMapping(value = "updateHeadPicByUid", produces = {"application/json;charset=UTF-8"})
    public Map<String,String> updateHeadPicByUid(User user, HttpServletRequest httpServletRequest, MultipartFile file){
        Map<String,String> map = Tools.uploadFile(httpServletRequest,"upload/user",file);
        if(((String)map.get("isSuccess")).equals("1")){
            user.setHeadpic(((String)map.get("url")));
            if(iUserService.updateUserByUid(user).get("result").toString().equals("1")){
                return map;
            }else {
                Map<String,String> map1 = new HashMap<String,String>();
                map1.put("isSuccess","-1");
                map1.put("url","");
                return map;
            }
        }else{
            return map;
        }
    }

    /**
     * 获取没有标注过的图片组 已测试
     * @param uid
     * @return
     */
    @RequestMapping("getUnFinishedGroup")
    public List<Classes> getUnFinishedGroup(Integer uid){
        return iClassService.getUnFinishedGroup(uid);
    }

    /**
     * 获取正在标注的图片组 已测试
     * @param uid
     * @return
     */
    @RequestMapping("getCurrentImageGroup")
    public List<Classes> getCurrentImageGroup(Integer uid){
        return iClassService.getCurrentImageGroup(uid);
    }

    /**
     * 获取历史记录 已测试
     * @param uid
     * @return
     */
    @RequestMapping("getHistoryRecord")
    public List<Classes> getHistoryRecord(Integer uid){
        return iClassService.getHistoryRecord(uid);
    }

    /**
     * 图片标注 未测试
     * @param label
     * @return
     */
    @RequestMapping("imageSubmit")
    public Map<String,Object> imageSubmit(@RequestBody List<Label> label,Integer recordid){
        return iLabelService.addLabel(label,recordid);
    }

    /**
     * 添加图片标注记录 已测试
     * @param imageRecord
     * @return
     */
    @RequestMapping("addImageRecord")
    public Map<String,Object> addImageRecord(ImageRecord imageRecord){
        return iClassService.addImageRecord(imageRecord);
    }

    /**
     * 根据关键字查找 已测试
     * @param key
     * @return
     */
    @RequestMapping("getImageByKey")
    public List<Image> getImageByKey(String key){
        return iimageService.obtainImageByKey(key);
    }





}
