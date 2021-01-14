package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
    public interface NotesMapper {

       @Select("SELECT * FROM NOTES where userId= #{id}")
       List<Notes> getAllNotes(Integer id);

        @Select("select * from Notes where noteid = #{id}")
        Notes findNote (Integer id);

        @Insert("insert into NOTES ( userId,notetitle,notedescription ) values ( #{userId},#{notetitle},#{notedescription} )  ")
        @Options(useGeneratedKeys = true, keyProperty = "noteid")
        int addNote(Notes Notes);

    @Update("UPDATE NOTES SET userId = #{userId}, notetitle = #{notetitle}, notedescription = #{notedescription}  WHERE noteid = #{noteid}")
    void updateNote(Notes Notes);

    @Delete("delete from NOTES where noteid = #{id}")
        void deleteNote(Integer id);
    }

