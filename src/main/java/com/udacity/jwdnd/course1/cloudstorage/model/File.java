package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer  userid;
    private Integer fileId;
    private String  filename;
    private String ContentType;
    private long fileSize;
    private byte[] FileData;

    public File(Integer fileId, String fileName, String contentType, long fileSize, Integer userId, byte[] fileData){
        this.fileId = fileId;
        this.filename = fileName;
        this.ContentType = contentType;
        this.fileSize = fileSize;
        this.userid = userId;
        this.FileData = fileData;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilename  () {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType  () {
        return ContentType;
    }

    public void setContentType(String contenttype) {
        this.ContentType = contenttype;
    }

    public long getFileSize  () {
        return fileSize;
    }

    public void setFileSize(long filesize) { this.fileSize = filesize; }

    public byte[] getFileData  () {
        return FileData;
    }

    public void setFileData(byte[] fileData) { this.FileData = fileData; }

}
