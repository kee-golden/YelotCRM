package com.yelot.crm.controller;


import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.Util.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangFei on 2014/11/7 0007.
 *         文件控制器
 */

@Controller
@RequestMapping("/file")
public class FileController {


    @ResponseBody
    @RequestMapping("/doc/upload")
    public ResultData excelUpload(Model model, @RequestParam(value = "fileName", required = false) MultipartFile excelFile, HttpServletRequest request, HttpServletResponse response) {

        ResultData resultData = ResultData.ok();
        String realPath;

        boolean isDebug = true;
        String deployPath = "";
        if (isDebug) {
            realPath = request.getServletContext().getRealPath("/") + File.separator;
        } else {
            realPath = deployPath+File.separator;
        }
//        String realPath = request.getServletContext().getRealPath("/");
        String suffix = (excelFile.getOriginalFilename().substring
                (excelFile.getOriginalFilename().lastIndexOf("."))).toLowerCase();
        /**拼成完整的文件保存路径加文件**/
        String filePath = realPath + "data" + File.separator;
        File docFile = new File(filePath);
        if (!docFile.exists()) {
            docFile.mkdirs();
        }
        //String fileName = excelFile.getOriginalFilename();
        String fileName = System.currentTimeMillis() + suffix;
        File file = new File(filePath + File.separator + fileName);
        try {
            resultData.putDataValue("path","/data/"+File.separator + fileName);
            excelFile.transferTo(file);
//            sendSuccessJSON(response, "/data/excel/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultData;
    }



}
