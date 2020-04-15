package edu.bjtu.sse.djd.dao;

import edu.bjtu.sse.djd.DBCP;
import edu.bjtu.sse.djd.entity.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDao {

    // 查找所有记录
    public List<Student> selectAll() {
        // 定义 SQL 语句
        String sql = "SELECT * FROM s_student";
        System.out.println(sql);
        // 一条结果
        Student s = null;
        // 存储结果集
        List<Student> resultList = new ArrayList<>();

        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = pstmt.executeQuery(sql)) {
                    // 输出结果集
                    while (resultSet.next()) {
                        // 初始化一条结果
                        s = new Student();
                        s.setId(resultSet.getLong("id"));
                        s.setName(resultSet.getString("name"));
                        s.setCreateTime(resultSet.getTimestamp("create_time"));
                        s.setUpdateTime(resultSet.getTimestamp("update_time"));
                        resultList.add(s);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // 添加一条记录
    public void addStudent(Student s) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "INSERT INTO s_student(id, name) VALUES (?, ?)";

        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, s.getId());
                pstmt.setString(2, s.getName());
                System.out.println(sql);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除一条记录
    public void deleteStudent(Student s) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "DELETE FROM s_student WHERE id=?";

        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, s.getId());
                System.out.println(sql);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新一条记录
    public void updateStudent(Student s) {
        // 定义 SQL 语句
        // PreparedStatement
        String sql = "UPDATE s_student SET name=?, update_time=? WHERE id=?";

        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, s.getName());
                pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                pstmt.setLong(3, s.getId());
                System.out.println(sql);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
