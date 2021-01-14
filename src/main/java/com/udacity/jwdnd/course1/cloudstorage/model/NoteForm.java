package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteForm {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Notes pageNotes;

    public String getNoteTitle() {
        return noteTitle;
    }
    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }
    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getNoteId() {
        return noteId;
    }
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Notes getPageNotes() {
        return pageNotes;
    }
    public void setPageNotes(Notes pageNotes) {
        this.pageNotes = pageNotes;
    }
}
