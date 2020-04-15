package edu.bjtu.sse.djd.controller;

import edu.bjtu.sse.djd.dao.HomeworkDao;
import edu.bjtu.sse.djd.entity.Homework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeworkController {

    private HomeworkDao homeworkDao = new HomeworkDao();

    @RequestMapping(value = "HomeworkTeacher", method = RequestMethod.GET)
    public void findAllHomeworkTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = homeworkDao.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/jsp/homework-teacher.jsp").forward(req, resp);
    }

    @RequestMapping(value = "HomeworkStudent", method = RequestMethod.GET)
    public void findAllHomeworkStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = homeworkDao.selectAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/jsp/homework-student.jsp").forward(req, resp);
    }

    @RequestMapping(value = "HomeworkTeacher", params = "method=addHomework", method = RequestMethod.POST)
    public void addHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Homework h = new Homework();
        h.setHomeworkTittle(req.getParameter("title"));
        h.setHomeworkContent(req.getParameter("content"));
        System.out.println(h.toString());
        homeworkDao.addHomework(h);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }

    @RequestMapping(value = "HomeworkTeacher", params = "method=updateHomework", method = RequestMethod.POST)
    public void updateHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Homework h = new Homework();
        h.setId(Long.parseLong(req.getParameter("homeworkId")));
        h.setHomeworkTittle(req.getParameter("title"));
        h.setHomeworkContent(req.getParameter("content"));
        homeworkDao.updateHomework(h);
        // 刷新页面
        resp.sendRedirect("HomeworkTeacher");
    }
}
