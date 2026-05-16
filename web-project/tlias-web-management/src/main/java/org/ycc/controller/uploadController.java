package org.ycc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.ycc.pojo.Result;
import org.ycc.utils.AliyunOSSOperator;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;



@RestController
@Slf4j
public class uploadController {

    /*
      本地磁盘存储方案
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接收参数：{},{},{}",name,age,file);
//        //获取原始文件名称
//        String originalFilename = file.getOriginalFilename();
//
//        //新的文件名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));   //截取原始名称的后缀，保证文件格式
//        String newFileName = UUID.randomUUID() + extension;   //将原始文件名称用RadomUUID来保证不会出现相同的文件名称，以此来覆盖
//
//        //保存文件
//        file.transferTo(new File("/Users/yuanchengle/Downloads/images/"+newFileName));
//
//        return Result.success();
//
//    }
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        log.info("url:{}",url);
        return Result.success(url);

    }
}
