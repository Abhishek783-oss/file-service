package com.abhishek.file_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse<T> {
    private String status;
    private String message;
    private T data;
}
