package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
    public interface CredentialsMapper {
        @Select("select * from Credentials where credentialid = #{id}")
        Credentials findCredential (Integer id);

        @Select("SELECT * FROM Credentials  where userId = #{id}")
        List<Credentials> getAllCredentials(Integer id);

        @Select("SELECT `key` FROM CREDENTIALS WHERE credentialId  = #{credentialId }")
        String findKey(@Param("credentialId") Integer credentialId);

        @Insert("insert into Credentials ( userId, url,username,key,password) values ( #{userId}  ,#{url},#{username},#{key},#{password})  ")
        @Options(useGeneratedKeys = true , keyProperty = "credentialid")
        int addCredential(Credentials Credentials);


        @Update("UPDATE FILES SET url = #{url}, username = #{username}, key = #{key}, password = #{password}, userId = #{userId} WHERE credentialid = #{credentialid}")
        void updateCredential(Credentials Credentials);


        @Delete("delete from Credentials where credentialid = #{id}")
        void deleteCredential(Integer id);
        }