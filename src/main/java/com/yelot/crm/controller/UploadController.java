package com.yelot.crm.controller;

import com.yelot.crm.Util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController {
    // 文件存储的根目录,放在程序运行的发布目录中，一般是在tomcat的安装同目录下
    String rootPath = "/upload/";


    Map<String, Object> fileIdMap = new HashMap<>();

    /**
     * 参数定义 CommonsMultipartFile 接收文件上传内容
     * AjaxResult  表示传达的是ajax数据
     *
     * @param file是不能改变的，因为webuploader是以file参数往后台 传文件的；ff是自定义的文件实体
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "up")
    public ResultData upload(@RequestParam(name = "modelPath", required = false) String modelPath,
                             @RequestParam(name = "file", required = false) CommonsMultipartFile file,
                             @RequestParam(defaultValue = "public") String model, HttpServletRequest request) throws IllegalStateException, IOException {

        // 判断是否有文件
        if (file != null && !file.isEmpty()) {
            // 获取文件的原始名称
            String oldName = file.getOriginalFilename();
            // 获取文件大小
            Long fileSize = file.getSize();
            // 获取文件的原始流
            // f.getInputStream()
            // 获取文件的类型
            System.out.println(file.getContentType());

            // 组装文件存储路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd");
            String dateStr = sdf.format(new Date());
            String filePath = rootPath + model + File.separator + dateStr;


        } else {
            System.out.println("上传失败！！");
        }
        return ResultData.ok();
    }

    @RequestMapping("test")
    public String uploadTest(){
        return "test/contract_edit";
    }
}