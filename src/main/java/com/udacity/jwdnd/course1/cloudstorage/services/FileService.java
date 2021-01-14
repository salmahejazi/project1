package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;
@Service
public class FileService {

    private  FilesMapper FilesMapper;

    public FileService(FilesMapper FilesMapper) {
        this.FilesMapper = FilesMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating FileService bean");
    }

   /* public void addFile(Files Files) {
        Files newFile = new Files();
        newFile.setFilename(Files.getFilename());
        newFile.setContentType(Files.getContentType());
        newFile.setFileSize(Files.getFileSize());
        newFile.setFileData(Files.getFileData());
        FilesMapper.addFile(newFile);
    }*/

    public List<Files> getAllFiles( ) {
        return FilesMapper.getAllFiles();
    }
    public int addFile(Files file){
        return FilesMapper.addFile(new Files(null, file.getFilename(), file.getContentType(), file.getFileSize(), file.getUserId(), file.getFileData()));
    }
    public void deleteFile(Integer fileId) {   FilesMapper.deleteFile(fileId); }

    public Files getFile(Integer fileId){ return FilesMapper.findFile(fileId); }

    public boolean isDuplicateFileName(Integer userId, String fileName)
    {
        return FilesMapper.findDuplicateFileName(userId, fileName) != null;
    }
}


