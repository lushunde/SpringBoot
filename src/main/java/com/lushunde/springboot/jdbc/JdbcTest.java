package com.lushunde.springboot.jdbc;

import com.lushunde.springboot.entity.UserInfo;

import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        UserInfo userInfo = new UserInfo();

        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开连接
            conn = DriverManager.getConnection("jdbc:mysql://10.0.193.128:3306/shard0", "root", "root");

            // 执行查询
            stmt = conn.createStatement();
            String sql = "SELECT user_id, user_name, account FROM user_info where user_id = 1";
            ResultSet rs = stmt.executeQuery(sql);

            // 获取结果集
            while (rs.next()) {
                Long userId = rs.getLong("user_id");
                String userName = rs.getString("user_name");
                String account = rs.getString("account");
                userInfo.setUserId(userId);
                userInfo.setUserName(userName);
                userInfo.setAccount(account);
            }
            System.out.println(userInfo);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
