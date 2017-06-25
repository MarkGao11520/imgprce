package com.hw.xyls.config;

import com.hw.xyls.dao.user.UserMapper;
import com.hw.xyls.pojo.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
public class CustomUserService implements UserDetailsService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    Md5PasswordEncoder md5PasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userMapper.getUserByName(s);
        System.out.println(md5PasswordEncoder.encodePassword("66666666",null));
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
//        user.setRoles(basUserMapper.findUserRoleByUserName(s));
        return user;
    }
}
