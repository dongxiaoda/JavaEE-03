package edu.bjtu.sse.djd.dao;

import edu.bjtu.sse.djd.DBCP;
import edu.bjtu.sse.djd.entity.StudentHomework;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentHomeworkDao {

    // 查找所有记录
    public List<StudentHomework> selectAll() {

        // 定义 SQL 语句
        String sql = "SELECT * FROM s_student_homework";
        System.out.println(sql);
        // 一条结果
        StudentHomework sh = null;
        // 存储结果集
        List<StudentHomework> resultList = new ArrayList<>();

        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = pstmt.executeQuery(sql)) {
                    // 输出结果集
                    while (resultSet.next()) {
                        // 初始化一条结果
                        sh = new StudentHomework(resultSet.getLong("id"), resultSet.getLong("student_id"),
                                resultSet.getLong("homework_id"), resultSet.getString("homework_title"),
                                resultSet.getString("homework_content"), resultSet.getTimestamp("create_time"), resultSet.getTimestamp("update_time"));
                        resultList.add(sh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // 添加一条记录
    public void addStudentHomework(StudentHomework sh) {

        // 定义 SQL 语句
        // PreparedStatement
        String sql = "INSERT INTO s_student_homework(student_id, homework_id, homework_title, homework_content) VALUES (?, ?, ?, ?)";
        // 执行 SQL
        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, sh.getStudentId());
                pstmt.setLong(2, sh.getHomeworkId());
                pstmt.setString(3, sh.getHomeworkTitle());
                pstmt.setString(4, sh.getHomeworkContent());
                System.out.println(sql);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除一条记录
    public void deleteStudentHomework(StudentHomework sh) {

        // 定义 SQL 语句
        // PreparedStatement
        String sql = "DELETE FROM s_student_homework WHERE id=?";

        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, sh.getId());
                System.out.println(sql);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新一条记录
    public void updateStudentHomework(StudentHomework sh) {

        // 定义 SQL 语句
        // PreparedStatement
        String sql = "UPDATE s_student_homework SET student_id=?, homework_id=?, homework_title=?, homework_content=?, update_time=? WHERE id=?";

        try (Connection connection = DBCP.getHikariDataSource().getConnection()){
            try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, sh.getStudentId());
                pstmt.setLong(2, sh.getHomeworkId());
                pstmt.setString(3, sh.getHomeworkTitle());
                pstmt.setString(4, sh.getHomeworkContent());
                pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                pstmt.setLong(6, sh.getId());
                System.out.println(sql);
                pstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
