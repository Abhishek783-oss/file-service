package com.abhishek.file_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FileResponseDTO {
    private String fileName;
    private String filePath;
    private long fileSize;
}
