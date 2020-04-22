package edu.bjtu.sse.djd.service;

import edu.bjtu.sse.djd.dao.StudentDao;
import edu.bjtu.sse.djd.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description TODO
 * @Author 董金达
 * @Date 2020/4/22 21:29
 * @Version 1.0
 **/

@Service
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> findAll(){
        return studentDao.selectAll();
    }

    public void addStudent(Student s){
        studentDao.addStudent(s);
    }

    public void updateStudent(Student s){
        studentDao.updateStudent(s);
    }
}
