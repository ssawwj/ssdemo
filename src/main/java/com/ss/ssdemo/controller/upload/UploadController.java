package com.ss.ssdemo.controller.upload;

import com.ss.ssdemo.bean.RespMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UploadController {
    @RequestMapping("toUpload")
    public String toUpload(){
        return "upload/upload";
    }

    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public RespMessage commonFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        try {
            String uploadFileName = file.getOriginalFilename();
            RespMessage resp = null;
            if (StringUtils.isBlank(uploadFileName)) {
                resp = new RespMessage(404, "文件不存在");
                return resp;
            }
            // 设置上传的路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"upload/";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String newFileName = new String(uploadFileName.getBytes(), "iso8859-1") + dateFormat.format(new Date().getTime()) + ".";
            File fi = new File(path + newFileName);
            file.transferTo(fi);
            return new RespMessage(200, newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new RespMessage(404, "文件转换出错");
        }
    }
}
