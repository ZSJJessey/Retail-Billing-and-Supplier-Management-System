package com.zhoushijie.demo.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

//文件管理控制类
@Controller
public class FileOptionController {
    //向文件上传页面跳转
    @GetMapping("/toUploadPage")
    public String toUploadPage() {
        return "page/upload";
    }

    //文件上传管理
    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile[] fileUpload, Model model) {
        //默认文件上传成功，
        model.addAttribute("uploadStatus", "上传成功");
        //foreach循环遍历
        for (MultipartFile file : fileUpload) {
            //获取文件名以及后缀名
            String fileName = file.getOriginalFilename();
            //1.提取文件名
            int index = fileName.lastIndexOf("\\");
            //2.通过下标截取文件名
            fileName = fileName.substring(index + 1);
            //3.重新生产文件名,得到新文件名（根据具体情况生成对应文件名）
            fileName = UUID.randomUUID() + "_" + fileName;//防止被覆盖
            //指定上传文件本地存储目录，if判断如果不存在需要提前创建
            String dirPath = "c:/file/";
            File filePath = new File(dirPath);
            if (!filePath.exists()) {//判断系统有没有这个文件夹
                filePath.mkdirs();//创建文件夹
            }
            try {
                file.transferTo(new File(dirPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
                //上传失败，返回失败信息
                model.addAttribute("uploadStatus", "上传失败：" + e.getMessage());
            }
        }
        //携带上传状态信息回调到文件上传页面
        return "page/upload";
    }

    @GetMapping("/toDownloadPage")

    public String toDownloadPage() {
        return "/Page/download";
    }

    //所以类型文件下载管理
    @Autowired
    HttpServletRequest request;

    @GetMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename) throws Exception {
        //下载目标文件的根路径
        String dirPath = "D:/s";
        //String dirPath="D:\\s";
        //创建文件对象
        File file = new File(dirPath + File.separator + filename);
        //设置响应头
        HttpHeaders httpHeaders = new HttpHeaders();

        filename = getFilename(request, filename);

        //通知浏览器以下载方式打开
        httpHeaders.setContentDispositionFormData("attachment", filename);
        //定义以流的形式 下载返回文件数据
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * 针对中文文件名的下载的解决方案
     *
     * @param request
     * @param filename
     * @return
     * @throws Exception
     */
    //根据浏览器的不同进行编码设置，返回编码后的文件名
    private String getFilename(HttpServletRequest request, String filename)
            throws Exception {
        //IE不同版本User-Agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        //请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示，并对转换的+进行更正
                return URLEncoder.encode(filename, "UTF-8").replace("+", " ");
            }
        }
        //火狐等其他浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }

}
