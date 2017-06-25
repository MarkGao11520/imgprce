package com.hw.xyls.tool;


import com.hw.xyls.datadictionary.DataDictionary;
import com.hw.xyls.datadictionary.DataObject;
import com.hw.xyls.pojo.user.SysUser;
import com.hw.xyls.service.sms.RandomUtils;
import com.hw.xyls.service.sms.SmsBase;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/5/7.
 */
public class Tools {

    public static Map<String,Object> sendMobileCode(String phone)  {
        Map<String,Object> map = new HashMap<String, Object>();
        SmsBase smsBase = new SmsBase();
        String msgcode = RandomUtils.msgCode();
        String content = "感谢您使用驯鹿，您当前的验证码为:" + msgcode + ",有效时间为60秒，请在时效内使用。如非本人发起请与我们联系。";
        try {
            if (Integer.parseInt(smsBase.SendSms(phone, content)) > 0) {
                DataObject dataObject = new DataObject();
                dataObject.setCreateTime(new Date());
                dataObject.setContent(msgcode);
                String id = UUID.randomUUID().toString();
                DataDictionary.DataMap.put(id, dataObject);
                map.put("id",id);
                map.put("isSuccess",1);
            }else {
                map.put("id","");
                map.put("isSuccess",0);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            map.put("id","");
            map.put("isSuccess",0);
        }finally {
            return map;
        }
    }

    /**
     *
     * @param request   获取地址用的
     * @param uploadPath   具体路径
     * @param multipartFile   上传的文件
     * @return
     */
    public static Map<String,String> uploadFile(HttpServletRequest request, String uploadPath, MultipartFile multipartFile){
        Map<String,String> map = new HashMap<String,String>();
        try {
            UUID uuid = UUID.randomUUID();
            String path = request.getServletContext().getRealPath(
                    "/");
            String userPath = "/" + uploadPath+"/";
            String realPath = path + userPath ;
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filename = uuid.toString()+"."+multipartFile.getContentType().split("/")[1].toString();
            String fileurl = realPath+filename;
            multipartFile.transferTo(new File(fileurl));
            map.put("isSuccess","1");
            map.put("url",userPath.substring(1,userPath.length())+filename);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isSuccess","0");
            map.put("url","");
        }finally {
            return map;
        }
    }

    public static SysUser obtainPrincipal(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        SysUser sysUser = new SysUser();
        if (principal instanceof UserDetails) {
            sysUser = (SysUser) principal;
        }
        return sysUser;
    }

    public static String dataLongToString(long time){
        Date dt=new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(dt);
    }

    public static Long dataStringToLong(String time) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt=sdf.parse(time);
            return dt.getTime();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
