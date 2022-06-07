package com.lushunde.springboot.mapper;

import com.lushunde.springboot.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qingshan
 */
@Mapper
public interface UserInfoMapper {

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
