package com.lushunde.springboot.mapper;

import com.lushunde.springboot.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author qingshan
 */
@Mapper
public interface OrderItemMapper {

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}
