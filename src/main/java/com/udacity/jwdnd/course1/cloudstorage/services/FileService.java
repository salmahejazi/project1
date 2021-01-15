package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;
@Service
public class FileService {

    private FileMapper FileMapper;

    public FileService(FileMapper FileMapper) {
        this.FileMapper = FileMapper;
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

    public List<File> getAllFiles(Integer userId ) {
        return FileMapper.getAllFiles(userId);
    }
    public int addFile(File file){
        return FileMapper.addFile(new File(null, file.getFilename(), file.getContentType(), file.getFileSize(), file.getUserId(), file.getFileData()));
    }

    public Integer deleteFile(Integer id) {
        return FileMapper.deleteFile(id);
    }

    public File getFile(Integer id, Integer userId) {
        return FileMapper.selectFileByIdAndUserId(id, userId);
    }

    public boolean isDuplicateFileName(Integer userId, String fileName)
    {
        return FileMapper.findDuplicateFileName(userId, fileName) != null;
    }
}


