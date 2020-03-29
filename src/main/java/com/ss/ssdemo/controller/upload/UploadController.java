package com.ss.ssdemo.controller.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class UploadController {
    @RequestMapping("toUpload")
    public String toUpload(){
        return "upload/upload";
    }

    @PostMapping("upload")
    @ResponseBody
    public  String  upload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file){
        if(!file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name+"-uploaded")));
                stream.write(bytes);
                stream.close();
                System.out.println("1111");
                return "上传成功";
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("222");
                return "上传异常："+e.getMessage();
            }

        }else{
            System.out.println("333");
            return "文件为空"; }
    }
}
