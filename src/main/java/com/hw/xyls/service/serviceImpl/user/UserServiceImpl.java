package com.hw.xyls.service.serviceImpl.user;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hw.xyls.dao.user.LoginMapper;
import com.hw.xyls.dao.user.UserMapper;
import com.hw.xyls.pojo.user.Login;
import com.hw.xyls.pojo.user.User;
import com.hw.xyls.pojo.user.UserDto;
import com.hw.xyls.service.user.IUserService;
import com.hw.xyls.tool.ChineseToEnglish;
import com.hw.xyls.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by gaowenfeng on 2017/5/7.
 */
@Service
@Scope("prototype")
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    /**
     * 验证用户是否已经存在
     *
     * @param uname
     * @return
     */
    @Override
    public boolean isExists(String uname) {
        if (loginMapper.checkIsExists(uname) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 注册
     *
     * @param login
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> register(Login login, User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            login.setLoginTime(new Date().getTime());
            loginMapper.insertSelective(login);
            login = loginMapper.checkIsExists(login.getUname());
            if (login != null) {
                user.setNikename(login.getUname());
                user.setUid(login.getId());
                user.setRoleid(3);
                userMapper.insertSelective(user);
                map.put("user", user);
                map.put("login", login);
                map.put("result", 1);
            } else {
                throw new RuntimeException("插入异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入异常");
        } finally {
            return map;
        }
    }

    @Override
    @Transactional
    public Map<String, Object> addAdmin(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Login login = new Login();
            login.setUname(ChineseToEnglish.getPingYin(user.getNikename()));
            login.setPassword(md5PasswordEncoder.encodePassword("66666666", null));
            login.setLoginTime(new Date().getTime());
            loginMapper.insertSelective(login);
            login = loginMapper.checkIsExists(login.getUname());
            if (login != null) {
                user.setUid(login.getId());
                user.setRoleid(2);
                userMapper.insertSelective(user);
                map.put("user", user);
                map.put("login", login);
                map.put("result", 1);
            } else {
                throw new RuntimeException("插入异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("插入异常");
        } finally {
            return map;
        }
    }

    /**
     * 修改密码
     *
     * @param login
     * @return
     */
    @Override
    public Map<String, Object> updatePassword(Login login) {
        Map<String, Object> map = new HashMap<String, Object>();
        Login result = loginMapper.checkIsExists(login.getUname());
        if (result != null) {
            login.setId(result.getId());
            if (loginMapper.updateByPrimaryKeySelective(login) > 0) {
                map.put("result", "1");
            }
        } else {
            map.put("result", "0");
        }
        return map;
    }

    @Override
    public Map<String, Object> updatePassword(String oldP, String newP) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (loginMapper.updatePassword(Tools.obtainPrincipal().getUsername(),md5PasswordEncoder.encodePassword(oldP,null),md5PasswordEncoder.encodePassword(newP,null)) == 1) {
                map.put("result", "1");
            } else {
                map.put("result", "0");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "0");
        }finally {
            return map;
        }
    }

    /**
     * 登录
     *
     * @param login
     * @return
     */
    @Override
    public Map<String, Object> login(Login login) {
        Map<String, Object> map = new HashMap<String, Object>();
        Login result = loginMapper.checkIsExists(login.getUname());
        if (result != null) {
            if (login.getPassword().equals(result.getPassword())) {
                result.setPassword(null);
                result.setLoginTime(new Date().getTime());
                loginMapper.updateByPrimaryKeySelective(result);
                map.put("user", userMapper.selectByPrimaryKey(result.getId()));
                map.put("login", login);
                map.put("result", 1);
            } else {
                map.put("user", "");
                map.put("login", "");
                map.put("result", -1);
            }
        } else {
            map.put("user", "");
            map.put("login", "");
            map.put("result", -1);
        }
        return map;
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> updateUserByUid(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userMapper.updateByPrimaryKeySelective(user) > 0) {
            map.put("result", "1");
        } else {
            map.put("result", "0");
        }
        return map;
    }

    /**
     * 修改
     *
     * @param login
     * @return
     */
    @Override
    public Map<String, Object> updatePhoneByUid(Login login) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (loginMapper.updateByPrimaryKeySelective(login) > 0) {
            map.put("result", "1");
        } else {
            map.put("result", "0");
        }
        return map;
    }

    @Override
    public PageInfo<User> obtainAdminList(int page, int rows, UserDto userDto) {
        PageHelper.startPage(page, rows);
        if (userDto.getNikeName() != "" && userDto.getNikeName() != null) {
            userDto.setNikeName("%" + userDto.getNikeName() + "%");
        } else {
            userDto.setNikeName("%%");
        }
        List<User> users = userMapper.getAdminList(userDto);
        if (resultHandler(users)) {
            return new PageInfo<User>(users);
        } else {
            return new PageInfo<User>(Collections.emptyList());
        }
    }

    @Override
    public User obtainLoginAdmin() {
        return userMapper.selectByPrimaryKey(Tools.obtainPrincipal().getId());
    }

    private boolean resultHandler(List<User> users) {
        if (users == null || users.size() == 0) {
            return false;
        } else {
            return true;
        }
    }


}
