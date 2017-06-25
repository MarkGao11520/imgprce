package com.hw.xyls.service.user;

import com.github.pagehelper.PageInfo;
import com.hw.xyls.pojo.user.Login;
import com.hw.xyls.pojo.user.User;
import com.hw.xyls.pojo.user.UserDto;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/5/7.
 */
public interface IUserService {
    /**
     * 验证用户是否已经存在
     * @param uname
     * @return
     */
    boolean isExists(String uname);

    /**
     * 注册
     * @param login
     * @param user
     * @return
     */
    Map<String, Object> register(Login login, User user);

    /**
     * 新增管理员
     * @param user
     * @return
     */
    Map<String, Object> addAdmin(User user);

    /**
     * 修改密码
     * @param login
     * @return
     */
    Map<String, Object> updatePassword(Login login);

    Map<String, Object> updatePassword(String oldP,String newP);

    /**
     * 登录
     * @param login
     * @return
     */
    Map<String,Object> login(Login login);

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    Map<String,Object> updateUserByUid(User user);

    /**
     * 修改手机号
     * @param login
     * @return
     */
    Map<String,Object> updatePhoneByUid(Login login);

    /**
     * 获取管理员列表
     * @param page
     * @param rows
     * @param userDto
     * @return
     */
    PageInfo<User> obtainAdminList(int page, int rows, UserDto userDto);

    /**
     * 获取当前登陆用户
     * @return
     */
    User obtainLoginAdmin();
}
