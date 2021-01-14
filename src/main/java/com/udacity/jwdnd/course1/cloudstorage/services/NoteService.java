package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {
    private NotesMapper NotesMapper;

    public NoteService(NotesMapper NotesMapper) {
        this.NotesMapper = NotesMapper;
    }

    public Notes getNotes(Integer noteid) {
        return NotesMapper.findNote(noteid);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NoteService bean");
    }

    public void addNote(NoteForm noteForm) {
        Notes notes = NotesMapper.findNote(noteForm.getNoteId());
        if (notes != null) {
            notes.setNotetitle(noteForm.getNoteTitle());
            notes.setNotedescription(noteForm.getNoteDescription());
           NotesMapper.updateNote(notes);
        } else {
            notes = new Notes();
            notes.setNotetitle(noteForm.getNoteTitle());
            notes.setNotedescription(noteForm.getNoteDescription());
            NotesMapper.addNote(notes);
        }
    }

    public  List<Notes> getAllNotes(Integer userId){
        return NotesMapper.getAllNotes(userId);
    }

    public void deleteNote(Integer noteId) {   NotesMapper.deleteNote(noteId); }

    public void updateNotes(Notes Notes){

          NotesMapper.updateNote(Notes); }


}
