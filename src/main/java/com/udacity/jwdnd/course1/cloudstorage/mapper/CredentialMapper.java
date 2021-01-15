package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
    public interface CredentialMapper {
        @Select("select * from Credentials where credentialid = #{id}")
        Credential findCredential (Integer id);

        @Select("SELECT * FROM Credentials  where userId = #{id}")
        List<Credential> getAllCredentials(Integer id);

        @Select("SELECT `key` FROM CREDENTIALS WHERE credentialId  = #{credentialId }")
        String findKey(@Param("credentialId") Integer credentialId);

        @Insert("insert into Credentials ( userId, url,username,key,password) values ( #{userId}  ,#{url},#{username},#{key},#{password})  ")
        @Options(useGeneratedKeys = true , keyProperty = "credentialid")
        void addCredential(Credential Credential);


        @Update("UPDATE FILES SET url = #{url}, username = #{username}, key = #{key}, password = #{password}, userId = #{userId} WHERE credentialid = #{credentialid}")
        void updateCredential(Credential Credential);


        @Delete("delete from Credentials where credentialid = #{id}")
        Integer deleteCredential(Integer id);
        }