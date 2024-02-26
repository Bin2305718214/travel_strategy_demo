package com.travel.controller;

import com.travel.result.Result;
import com.travel.utils.FileUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Build_start
 */
@Api(tags = "文件上传和下载")
@RestController
@RequestMapping("/images")
public class UpDownLoadController {

    @Value("${travel-strategy.path}")
    private String basePath;

    /**
     *文件的上传
     * @param file 参数名字必须和form表单的name字段名字相同
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        // file是一个临时文件，需要指定到转存位置，如果不转存的话，本次请求完成之后临时文件就会删除

        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 使用UUID重新生成文件名，防止文件名称重复文件覆盖
        String fileName = UUID.randomUUID().toString()+suffix;
        // 判断目录是否存在，不存在就创建
        File dir = new File(basePath);
        if (!dir.exists()){
            // 目录不存在，创建
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok(fileName);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(@RequestParam("fileName") String name, HttpServletResponse response){
        // 输入流，通过输入流读取文件内容
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(basePath + name));
            // 输出流，通过输出流将文件写回浏览器，在浏览器展示图片

            outputStream = response.getOutputStream();

            response.setContentType("/image/jepg");
            byte[] bytes = new byte[1024];
            int len=0;
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            };
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除对应文件
     * @param name 文件名
     */
    @PostMapping("/delete")
    public Result<String> deleteImg(@RequestBody String name) {
        name = name.split("=")[0];

        boolean flag = FileUtils.delete(basePath, name);

        if (flag) {
            return Result.ok();
        } else {
            return Result.build(305,"照片删除失败！");
        }
    }

    /**
     * 删除所有提交的图片
     * @param postImgList 图片名称列表
     * @return
     */
    @PostMapping("/deleteAll")
    public Result deleteAll(@RequestBody List<String> postImgList) {

        boolean flag = FileUtils.deleteAll(basePath, postImgList);

        if (flag) {
            return Result.ok();
        } else {
            return Result.build(303,"照片删除失败！");
        }
    }

}
