package com.trasportManagement.transportservice.service;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageUploadService {
    //public final String UPLOAD_DIR = "src\\main\\resources\\static\\image";
    public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();

    public ImageUploadService() throws IOException {
    }


    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    public File uploadImage(MultipartFile multipartFile) {
        System.out.println(UPLOAD_DIR);
        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
            //File file = this.convertToFile(multipartFile, fileName);

            Files.copy(multipartFile.getInputStream() ,Paths.get(UPLOAD_DIR + File.separator + fileName),StandardCopyOption.REPLACE_EXISTING);

            //multipartFile.transferTo(new File(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()));
            File file = this.convertToFile(multipartFile, fileName);
            return file;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
