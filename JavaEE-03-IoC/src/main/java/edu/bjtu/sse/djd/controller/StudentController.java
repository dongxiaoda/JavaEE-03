package edu.bjtu.sse.djd.controller;

import edu.bjtu.sse.djd.dao.StudentDao;
import edu.bjtu.sse.djd.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentDao studentDao;

    @RequestMapping(value = "Student", method = RequestMethod.GET)
    public void selectStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentDao.selectAll();
        req.setAttribute("studentList", studentList);

        req.getRequestDispatcher("/jsp/teacher.jsp").forward(req, resp);
    }

    @RequestMapping(value = "Student", params = "method=addStudent", method = RequestMethod.POST)
    protected void addStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Student s = new Student();
        s.setId(Long.parseLong(req.getParameter("studentId")));
        s.setName(req.getParameter("studentName"));
        studentDao.addStudent(s);
        // 刷新页面
        resp.sendRedirect("Student");
    }

    @RequestMapping(value = "Student", params = "method=updateStudent", method = RequestMethod.POST)
    protected void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Student s = new Student();
        s.setId(Long.parseLong(req.getParameter("studentId")));
        s.setName(req.getParameter("studentName"));
        studentDao.updateStudent(s);
        // 刷新页面
        resp.sendRedirect("Student");
    }
}
