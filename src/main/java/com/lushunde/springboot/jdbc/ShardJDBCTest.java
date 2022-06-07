//package com.lushunde.springboot.jdbc;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @Author: qingshan
// */
//public class ShardJDBCTest {
//    public static void main(String[] args) throws SQLException {
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//        // 配置第一个数据源
//        DruidDataSource dataSource1 = new DruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://192.168.44.121:3306/ds0");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("123456");
//        dataSourceMap.put("ds0", dataSource1);
//
//        // 配置第二个数据源
//        DruidDataSource dataSource2 = new DruidDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://192.168.44.121:3306/ds1");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("123456");
//        dataSourceMap.put("ds1", dataSource2);
//
//        // 配置Order表规则
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order", "ds${0..1}.t_order");
//        // 分库策略，使用inline实现
//        InlineShardingStrategyConfiguration dataBaseInlineStrategy = new InlineShardingStrategyConfiguration("order_id", "ds${order_id % 2}");
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(dataBaseInlineStrategy);
//
//        // 分表策略，使用inline实现（没有分表，为什么不分表？）
//        InlineShardingStrategyConfiguration tableInlineStrategy = new InlineShardingStrategyConfiguration("order_id", "t_order");
//        orderTableRuleConfig.setTableShardingStrategyConfig(tableInlineStrategy);
//
//        // 添加表配置
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//
//        // 获取数据源对象
//        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
//
//        String sql = "SELECT * from t_order WHERE order_id =?";
//        try {
//            Connection conn = dataSource.getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setLong(1, 2);
//            System.out.println();
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    // %2结果，路由到
//                    System.out.println("---------order_id：" + rs.getLong(1));
//                    System.out.println("---------user_id：" + rs.getLong(2));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}