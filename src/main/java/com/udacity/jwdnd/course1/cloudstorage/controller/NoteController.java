package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @ModelAttribute("noteForm")
    public NoteForm getNoteForm() {
        return new NoteForm();
    }

//     After the page has initially been loaded it should do be able to add notes to the page
    @PostMapping("/notes")
    public String postNewNote(@ModelAttribute("noteForm") NoteForm noteForm, Model model) {
        this.noteService.addNote(noteForm);
        return "redirect:/home";
    }

    @GetMapping("/notes/{noteId}")
    public String deleteNote(@PathVariable(value = "noteId") Integer noteId, Model model) {
        noteService.deleteNote(noteId);
        return "redirect:/home";
    }

    @GetMapping(value= "/getNote/{noteId}")
        @ResponseBody
        public Notes getNote(@PathVariable(name = "noteId") String noteID) {
            Integer noteId = Integer.parseInt(noteID);
            return noteService.getNotes(noteId);
        }
}
