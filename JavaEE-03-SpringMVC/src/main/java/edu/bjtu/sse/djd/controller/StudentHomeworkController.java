package edu.bjtu.sse.djd.controller;

import edu.bjtu.sse.djd.dao.StudentHomeworkDao;
import edu.bjtu.sse.djd.entity.StudentHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentHomeworkController {

    private StudentHomeworkDao studentHomeworkDao = new StudentHomeworkDao();

    @RequestMapping(value = "StudentHomework", method = RequestMethod.GET)
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentHomework> list = studentHomeworkDao.selectAll();
        req.setAttribute("studentHomeworkList",list);
        req.getRequestDispatcher("/jsp/studentHomework.jsp").forward(req, resp);
    }

    @RequestMapping(value = "StudentHomework", method = RequestMethod.POST)
    public void addStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        StudentHomework sh = new StudentHomework();
        sh.setStudentId(Long.parseLong(req.getParameter("studentId")));
        sh.setHomeworkId(Long.parseLong(req.getParameter("homeworkId")));
        sh.setHomeworkTitle(req.getParameter("title"));
        sh.setHomeworkContent(req.getParameter("content"));
        System.out.println(sh.toString());
        studentHomeworkDao.addStudentHomework(sh);
        // 刷新页面
        resp.sendRedirect("HomeworkStudent");
    }


}
