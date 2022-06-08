package com.lushunde.springboot.mapper;

import com.lushunde.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/8 15:09
 * @Version 1.0.0
 **/
@Mapper
public interface UserMapper {

    User selectByPrimaryKey(Integer userId) ;
}
