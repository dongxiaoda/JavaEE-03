package edu.bjtu.sse.djd.aspect;

import edu.bjtu.sse.djd.DBCP;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author 董金达
 * @version 1.0
 * @name HomeworkAspect
 * @date 2020/4/22 22:16
 **/

@Aspect
@EnableAspectJAutoProxy
@Component
public class HomeworkAspect {

    /**
     * @param
     * @return void
     * @author 董金达
     * @date 23:38 2020/4/22
     * @description 通过类定义切点
     **/
    @Pointcut("execution(* edu.bjtu.sse.djd.service.HomeworkService.*(..))")
    private void homeworkPointcut() {
    }

    /**
     * @author 董金达
     * @date 23:36 2020/4/22
     * @description 事务管理开启，关闭数据库自动提交
     * @param
     * @return void
     **/
//    @Before("homeworkPointcut()")
//    public void before() throws SQLException {
//        Connection connection = Objects.requireNonNull(DBCP.getHikariDataSource()).getConnection();
//        DBCP.getHikariDataSource().setAutoCommit(false);
//        connection.setAutoCommit(false);
//        System.out.println("数据库自动提交 已关闭，事务管理已开启！");
//        connection.close();
//    }

    /**
     * @param
     * @return void
     * @author 董金达
     * @date 23:50 2020/4/22
     * @description 提交事务
     **/
    @AfterReturning("homeworkPointcut()")
    public void commit() throws SQLException {
        Connection connection = Objects.requireNonNull(DBCP.getHikariDataSource()).getConnection();
        connection.setAutoCommit(true);
        System.out.println("数据库自动提交已开启，事务管理关闭！");
        connection.close();
    }

    /**
     * @param
     * @return void
     * @author 董金达
     * @date 23:52 2020/4/22
     * @description 提交事务出错，回滚
     **/
    @AfterThrowing("homeworkPointcut()")
    public void rollback() throws SQLException {
        Connection connection = Objects.requireNonNull(DBCP.getHikariDataSource()).getConnection();
        connection.setAutoCommit(false);
        connection.rollback();
        System.out.println("提交事务失败，回滚事务！");
        connection.close();
    }

    /**
     * @param
     * @return void
     * @author 董金达
     * @date 23:48 2020/4/22
     * @description 事务管理关闭，开启数据库自动提交
     **/
    @After("homeworkPointcut()")
    public void after() throws SQLException {
        Connection connection = Objects.requireNonNull(DBCP.getHikariDataSource()).getConnection();
        connection.setAutoCommit(false);
        System.out.println("数据库自动提交已关闭，事务管理已开启！");
        connection.commit();
        System.out.println("提交！");
        connection.close();
    }

}
