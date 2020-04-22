package edu.bjtu.sse.djd.controller;

import edu.bjtu.sse.djd.entity.Homework;
import edu.bjtu.sse.djd.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeworkController {

    private final HomeworkService homeworkService;

    @Autowired
    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @RequestMapping(value = "HomeworkTeacher", method = RequestMethod.GET)
    public void findAllHomeworkTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", homeworkService.findAll());
        req.getRequestDispatcher("/jsp/homework-teacher.jsp").forward(req, resp);
    }

    @RequestMapping(value = "HomeworkStudent", method = RequestMethod.GET)
    public void findAllHomeworkStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", homeworkService.findAll());
        req.getRequestDispatcher("/jsp/homework-student.jsp").forward(req, resp);
    }

    @RequestMapping(value = "HomeworkTeacher", params = "method=addHomework", method = RequestMethod.POST)
    public void addHomework(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("utf-8");
        Homework h = new Homework(req.getParameter("title"), req.getParameter("content"));
        homeworkService.addHomework(h);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }

    @RequestMapping(value = "HomeworkTeacher", params = "method=updateHomework", method = RequestMethod.POST)
    public void updateHomework(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("utf-8");
        Homework h = new Homework(Long.parseLong(req.getParameter("homeworkId")), req.getParameter("title"), req.getParameter("content"));
        homeworkService.updateHomework(h);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }
}
