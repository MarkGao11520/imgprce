package com.hw.xyls.dao.user;

import com.hw.xyls.pojo.user.Login;
import com.hw.xyls.pojo.user.SysUser;
import com.hw.xyls.pojo.user.User;
import com.hw.xyls.pojo.user.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select uId as uid, nikeName, roleid, sex, habit, age, professional, brithday, qq, wechat, headPic from tb_user where roleid = 2 and nikeName like #{userDto.nikeName}")
    @Results(
            @Result(column="uId",property="login",one=@One(select = "com.hw.xyls.dao.user.LoginMapper.selectByPrimaryKey1"))
    )
    List<User> getAdminList(@Param("userDto")UserDto userDto);

    @Select("select id, uname as username, password from tb_login where uname=#{name}")
    SysUser getUserByName(String name);
}