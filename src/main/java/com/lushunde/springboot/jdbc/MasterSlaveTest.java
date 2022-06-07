//package com.lushunde.springboot.jdbc;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @Author: qingshan
// */
//public class MasterSlaveTest {
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
//        dataSourceMap.put("master0", dataSource1);
//
//        // 配置第二个数据源
//        DruidDataSource dataSource2 = new DruidDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://192.168.44.128:3306/ds0");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("123456");
//        dataSourceMap.put("slave0", dataSource2);
//
//        // 配置读写分离规则
//        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("qs_master_slave", "master0", Arrays.asList("slave0"));
//
//        // 获取数据源对象
//        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig, new Properties());
//        Connection conn = dataSource.getConnection();
//
//        String selectSql = "SELECT * from t_order WHERE order_id =?";
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
//            // 直接在 slave 128 ds0 插入主节点没有的数据： insert into t_order(order_id, user_id) value(26732,26732)
//            preparedStatement.setLong(1, 26732);
//            System.out.println();
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    System.out.println("---------order_id：" + rs.getLong(1));
//                    System.out.println("---------user_id：" + rs.getLong(2));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}