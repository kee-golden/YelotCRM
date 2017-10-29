package com.yelot.crm.controller;


import com.yelot.crm.AppConfig;
import com.yelot.crm.Util.DateUtil;
import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.Util.ResultData;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;


/**
 *
 List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
 if(files != null){

 }
 *
 * @author WangFei on 2014/11/7 0007.
 *         文件控制器
 */

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    AppConfig appConfig;


    /**
     * 单文件上传
     * @param model
     * @param multiFile
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public ResultData fileUpload(Model model, @RequestParam(value = "multiFile", required = false) MultipartFile multiFile, HttpServletRequest request) {

        ResultData resultData = ResultData.ok();
        String realPath;

        String deployPath = appConfig.getDataPath();
        if (appConfig.getDebug()) {
            realPath = request.getServletContext().getRealPath("/") + File.separator;
        } else {
            realPath = deployPath+File.separator;
        }
        String suffix = (multiFile.getOriginalFilename().substring
                (multiFile.getOriginalFilename().lastIndexOf("."))).toLowerCase();
        /**拼成完整的文件保存路径加文件**/
        String filePath = realPath + "data" + File.separator+ DateUtil.toString(new Date(),"yyMMdd")+File.separator;
        File docFile = new File(filePath);
        if (!docFile.exists()) {
            docFile.mkdirs();
        }
        String fileNameTemp = System.currentTimeMillis() + suffix;
        File file = new File(filePath + fileNameTemp);
        try {
            resultData.putDataValue("path","/data"+File.separator + DateUtil.toString(new Date(),"yyMMdd")+File.separator + fileNameTemp);
            //
            multiFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultData;
    }


}
