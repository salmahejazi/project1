package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private NoteMapper NoteMapper;

    public NoteService(NoteMapper NoteMapper) {
        this.NoteMapper = NoteMapper;
    }

    public Note getNotes(Integer noteid) {
        return NoteMapper.findNote(noteid);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NoteService bean");
    }

    public int addNote(Note note) {
        Integer noteId = note.getNoteid();
        if (noteId == null) {
            return NoteMapper.addNote(note);
        } else {
            if(NoteMapper.findNote(note.getNoteid())!= null){
                return NoteMapper.updateNote(note);
            }else{
                return NoteMapper.addNote(note);
            }
        }
    }

    public  List<Note> getAllNotes(Integer userId){
        return NoteMapper.getAllNotes(userId);
    }

    public void deleteNote(Integer noteId) {   NoteMapper.deleteNote(noteId); }

    public void updateNotes(Note Note){

          NoteMapper.updateNote(Note); }


}
