package com.hw.xyls.web;

import com.github.pagehelper.PageInfo;
import com.hw.xyls.pojo.image.Image;
import com.hw.xyls.pojo.image.ImageDto;
import com.hw.xyls.pojo.label.Result;
import com.hw.xyls.pojo.user.User;
import com.hw.xyls.pojo.user.UserDto;
import com.hw.xyls.service.image.IimageService;
import com.hw.xyls.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/6/12.
 */
@RestController
@Scope("prototype")
@RequestMapping("adminController")
public class AdminController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IimageService iimageService;

    @RequestMapping("addAdmin")
    public Map<String,Object> addAdmin(User user){
        try {
            return iUserService.addAdmin(user);
        }catch (Exception e){
            Map<String,Object> map = new HashMap<>();
            map.put("result",0);
            return map;
        }
    }

    @RequestMapping("updateP")
    public Map<String,Object> updateP(String oldP,String newP){
            return iUserService.updatePassword(oldP, newP);
    }

    @RequestMapping("getLoginUser")
    public User getLoginUser(){
        return iUserService.obtainLoginAdmin();
    }

    @RequestMapping("getAdminList")
    public PageInfo<User> getAdminList(int page, int rows, UserDto userDto){
        return iUserService.obtainAdminList(page, rows, userDto);
    }

    @RequestMapping("getImageList")
    public PageInfo<Image> getImageList(int page, int rows, ImageDto imageDto){
        return iimageService.obtainImageListByDto(page, rows, imageDto);
    }

    @RequestMapping("getResultList")
    public PageInfo<Result> getResultList(int page, int rows){
        return iimageService.obtainResultList(page, rows);
    }

    @RequestMapping("uploadImageBatch")
    public Map<String,Object> uploadImageBatch(MultipartFile zip, Integer uid){
        Map<String,Object> map=null;
        try{
            map = iimageService.doUploadImageBatchs(zip, uid);
        }catch (Exception e){
            map = new HashMap<String,Object>();
            map.put("result",0);
            e.printStackTrace();
        }finally {
            return map;
        }
    }
}
