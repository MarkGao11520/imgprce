package com.hw.xyls.dao.user;

import com.hw.xyls.pojo.user.Login;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.method.P;

public interface LoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Login record);

    int insertSelective(Login record);

    Login selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);

    /**
     * 检查会员是否存在
     * @param uname
     * @return
     */
    Login checkIsExists(String uname);

    @Update("update tb_login set password = #{npassword} where uname = #{uname} and password = #{password}")
    int updatePassword(@Param("uname")String uname, @Param("password")String password,@Param("npassword")String npassword);

}