package com.lushunde.springboot.controller;

import com.lushunde.springboot.config.result.R;
import com.lushunde.springboot.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName WebLofController
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/8 22:05
 * @Version 1.0.0
 **/

@Slf4j
@RestController
@RequestMapping("/log")
@Api(tags = "web统一参数打印测试")
public class WebLogController {


    /**
     * POST 方式接口测试
     * @param user
     * @return
     */
    @ApiOperation(value = "POST 方式接口测试")
    @PostMapping("/user")
    public User testPost(@RequestBody User user) {
        log.info("testPost ...");
        return user;
    }

    /**
     * GET 方式接口测试
     * @return
     */
    @ApiOperation(value = "GET 方式接口测试")
    @GetMapping("/user")
    public String testGet(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        log.info("testGet ...");
        return "success";
    }

    /**
     * 单文件上传接口测试
     * @return
     */
    @ApiOperation(value = "单文件上传接口测试")
    @PostMapping("/file/upload")
    public R<String> testFileUpload(@RequestPart("file") MultipartFile file) {
        log.info("testFileUpload ...");
        return R.ok("success");
    }

    /**
     * 多文件上传接口测试
     * @return
     */
    @ApiOperation(value = "多文件上传接口测试")
    @PostMapping("/multiFile/upload")
    public R<String> testMultiFileUpload(@RequestPart("file") MultipartFile[] file, User user) {
        log.info("testMultiFileUpload ...");
        return R.ok("success");
    }


}
