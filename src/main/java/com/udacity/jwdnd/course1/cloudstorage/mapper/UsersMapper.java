package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.*;




    @Mapper
    public interface UsersMapper {
        @Select("select * from Users where username = #{username}")
        Users findUser (String username);

        @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
        @Options(useGeneratedKeys = true, keyProperty = "userId")
        int insert(Users user);

    }

