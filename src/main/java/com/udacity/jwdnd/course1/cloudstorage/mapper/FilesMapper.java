package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
    public interface FilesMapper {
        @Select("select * from Files where fileId = #{id}")
        Files findFile (Integer id);

        @Select("select * from Files where userId = #{id} and filename =#{filename}")
        Files findDuplicateFileName (Integer id , String filename);

        //@Select("SELECT * FROM Files where userId =  #{id}")
        //List<Files> getAllFiles(Integer id);
        @Select("SELECT * FROM Files")
        List<Files> getAllFiles();
        @Insert("insert into Files ( userId, filename,contenttype,filesize,filedata) values ( #{userId}  ,#{filename},#{contenttype},#{filesize},#{filedata})  ")
        @Options(useGeneratedKeys = true , keyProperty = "fileId")
        int addFile(Files Files);

        @Update("UPDATE FILES SET filename = #{filename}, contenttype = #{contentType}, filesize = #{fileSize}, userid = #{userId}, filedata = #{fileData} WHERE fileId = #{fileId}")
        void updateFile(Files files);

        @Delete("delete from Files where fileId = #{id}")
        void deleteFile(Integer id);
    }

