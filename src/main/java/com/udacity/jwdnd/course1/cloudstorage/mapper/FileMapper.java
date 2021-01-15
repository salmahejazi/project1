package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
    public interface FileMapper {
        @Select("select * from Files where fileId = #{id}")
        File findFile (Integer id);

        @Select("select * from Files where userId = #{id} and filename =#{filename}")
        File findDuplicateFileName (Integer id , String filename);

        @Select("SELECT * FROM Files where userId =  #{id}")
        List<File> getAllFiles(Integer id);
        //@Select("SELECT * FROM Files")
        //List<File> getAllFiles();
        @Insert("insert into Files ( userId,fileId, filename,contenttype,filesize,filedata) values ( #{userId}  ,#{id},#{filename},#{contenttype},#{filesize},#{filedata})  ")
        @Options(useGeneratedKeys = true , keyProperty = "fileId")
        int addFile(File File);

        @Update("UPDATE FILES SET filename = #{filename}, contenttype = #{contentType}, filesize = #{fileSize}, userid = #{userId}, filedata = #{fileData} WHERE fileId = #{fileId}")
        void updateFile(File file);
    @Select("SELECT * FROM FILES WHERE fileId=#{id} AND userid=#{userId}")
    File selectFileByIdAndUserId(Integer id, Integer userId);
        @Delete("delete from Files where fileId = #{id}")
        Integer deleteFile(Integer id);
    }

