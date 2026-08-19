package com.success.oracle.controller;

import com.success.oracle.util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class SimpleUploadController {

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";  // 对应 src/main/resources/templates/upload.html
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            // 使用项目根目录下的 uploads 文件夹
            String uploadDir = "D:/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 保存文件
            String fileName = file.getOriginalFilename();
            File dest = new File(uploadDir + fileName);
            file.transferTo(dest);

            return "上传成功！保存位置：" + dest.getAbsolutePath();
        } catch (IOException e) {
            return "上传失败：" + e.getMessage();
        }
    }
}