package com.abhishek.file_service.service;

import com.abhishek.file_service.entity.FileMetadata;
import com.abhishek.file_service.repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private final FileMetadataRepository fileMetadataRepository;
    public FileService(FileMetadataRepository fileMetadataRepository){
        this.fileMetadataRepository=fileMetadataRepository;
    }

    public FileMetadata saveFileMetadata(String fileName, String path, long size){
        FileMetadata file = FileMetadata.builder()
                .fileName(fileName)
                .filePath(path)
                .fileSize(size)
                .build();
        return fileMetadataRepository.save(file);
    }

}
