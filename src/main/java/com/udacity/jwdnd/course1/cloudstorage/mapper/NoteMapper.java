package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
    public interface NoteMapper {

       @Select("SELECT * FROM NOTES where userId= #{id}")
       List<Note> getAllNotes(Integer id);

        @Select("select * from Notes where noteid = #{id}")
        Note findNote (Integer id);

        @Insert("insert into NOTES ( userId,notetitle,notedescription ) values ( #{userId},#{notetitle},#{notedescription} )  ")
        @Options(useGeneratedKeys = true, keyProperty = "noteid")
        int addNote(Note Note);

    @Update("UPDATE NOTES SET userId = #{userId}, notetitle = #{notetitle}, notedescription = #{notedescription}  WHERE noteid = #{noteid}")
    int updateNote(Note Note);

    @Delete("delete from NOTES where noteid = #{id}")
    Integer deleteNote(Integer id);
    }

