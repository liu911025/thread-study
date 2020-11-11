package com.example.demotest.controller;

import com.example.demotest.util.UploadHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文件上传
 */
@RestController
public class UploadController {


    @RequestMapping(value = "/uploadFile/upload", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request,
                             HttpServletResponse response) {
        List<MultipartFile> multipartFiles = UploadHelper.getFileSet(request, 1024 * 20, null);
        String path = "D:" + File.separator;
        if (multipartFiles.size() == 0) {
            // TODO 给出提示,不允许没选择文件点击上传

        }
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                String filePath = UploadHelper.uploadFile(multipartFile, path);
                System.out.println(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 拿到的imgPath就是图片的相对于contextPath的存储路径了
        }
        return null;
    }


    @RequestMapping(value = "/uploadFile/upload2", method = RequestMethod.POST)
    public String uploadFile2(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        String path = "D:/upload/" + format;
        File file1 = new File(path);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        if (null == files || files.length == 0) {
            System.out.println("上传文件为空！");
        }

        for (MultipartFile file : files) {
            if (!checkFile(file.getOriginalFilename())) {
                return "failed";
            }
            file.transferTo(new File(path + File.separator + file.getOriginalFilename()));
        }
        return "success";
    }

    /**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    private boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "pdf";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
}
