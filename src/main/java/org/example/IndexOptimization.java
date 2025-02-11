package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class IndexOptimization {
    // MySQL 연결 정보
    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USER = "root";
    private static final String PASSWORD = "my-secret-pw";

    public void bulkInsert() {
        String sql = "INSERT INTO customers (first_name, last_name, email, city) values (?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            connection.setAutoCommit(false);

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (int i = 1; i <= 100000; i++) {
                    preparedStatement.setString(1, "FirstName_" + i);
                    preparedStatement.setString(2, "LastName_" + i);
                    preparedStatement.setString(3, "user" + i + "@example.com");
                    preparedStatement.setString(4, "Seoul");

                    preparedStatement.addBatch();  // 배치에 추가

                    // 매 1000개마다 실행하여 메모리 관리
                    if (i % 1000 == 0) {
                        preparedStatement.executeBatch();
                        connection.commit();
                        System.out.println(i + " rows inserted...");
                    }
                }
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

