package com.lushunde.springboot.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName User
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/7 23:59
 * @Version 1.0.0
 **/
@Data
@ApiModel(value = "Mybatis用户查询演示")
public class User {
    private Integer userId;
    private String userName;
    private Integer sex;
    private Integer age;
    private String address;

}
