package com.abhishek.file_service.controller;

import com.abhishek.file_service.dto.APIResponse;
import com.abhishek.file_service.dto.FileResponseDTO;
import com.abhishek.file_service.service.FileService;
import com.abhishek.file_service.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;
    private final S3Service s3Service;
    public FileController(FileService fileService, S3Service s3Service){
        this.fileService = fileService;
        this.s3Service=s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<APIResponse<FileResponseDTO>> uploadFile(@RequestParam MultipartFile file) throws IOException {

//            String uploadDir = System.getProperty("user.dir")+"/uploads/";
//            File dir=new File(uploadDir);
//            if(!dir.exists()){
//                dir.mkdirs();
//            }
//            String filename = file.getOriginalFilename();
//            if(filename.isEmpty()){
//                return ResponseEntity.badRequest().body(APIResponse.<FileResponseDTO>builder()
//                                .status("error")
//                                .message("file is empty")
//                        .build());
//            }
//            String filePath = uploadDir+filename;
//            System.out.println("Saving file to: "+filePath);
//            file.transferTo(new File(filePath));
//            fileService.saveFileMetadata(filename, filePath, file.getSize());
        String fileUrl = s3Service.uploadFile(file);
            FileResponseDTO fileResponseDTO = FileResponseDTO.builder()
                    .fileName(file.getOriginalFilename())
                    .filePath(fileUrl)
                    .fileSize(file.getSize())
                    .build();
            APIResponse<FileResponseDTO> apiResponse = APIResponse.<FileResponseDTO>builder()
                    .status("success")
                    .message("File uploaded successfully")
                    .data(fileResponseDTO)
                    .build();
            return ResponseEntity.ok(apiResponse);

    }
}
