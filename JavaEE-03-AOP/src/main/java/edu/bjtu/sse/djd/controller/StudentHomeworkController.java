package edu.bjtu.sse.djd.controller;

import edu.bjtu.sse.djd.entity.StudentHomework;
import edu.bjtu.sse.djd.service.StudentHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class StudentHomeworkController {

    private StudentHomeworkService studentHomeworkService;

    @Autowired
    public StudentHomeworkController(StudentHomeworkService studentHomeworkService) {
        this.studentHomeworkService = studentHomeworkService;
    }

    @RequestMapping(value = "StudentHomework", method = RequestMethod.GET)
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("studentHomeworkList", studentHomeworkService.findAll());
        req.getRequestDispatcher("/jsp/studentHomework.jsp").forward(req, resp);
    }

    @RequestMapping(value = "StudentHomework", method = RequestMethod.POST)
    public void addStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        StudentHomework sh = new StudentHomework(Long.parseLong(req.getParameter("studentId")),
                Long.parseLong(req.getParameter("homeworkId")), req.getParameter("title"), req.getParameter("content"));
        studentHomeworkService.addStudentHomework(sh);
        // 刷新页面
        resp.sendRedirect("HomeworkStudent");
    }


}
