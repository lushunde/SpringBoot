package com.lushunde.springboot.mapper;

import com.lushunde.springboot.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qingshan
 */
@Mapper
public interface OrderMapper {

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
