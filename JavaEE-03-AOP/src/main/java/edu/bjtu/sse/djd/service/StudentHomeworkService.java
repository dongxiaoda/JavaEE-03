package edu.bjtu.sse.djd.service;

import edu.bjtu.sse.djd.dao.StudentHomeworkDao;
import edu.bjtu.sse.djd.entity.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StudentHomeworkService
 * @Description TODO
 * @Author 董金达
 * @Date 2020/4/22 21:30
 * @Version 1.0
 **/

@Service
public class StudentHomeworkService {

    private StudentHomeworkDao studentHomeworkDao;

    @Autowired
    public StudentHomeworkService(StudentHomeworkDao studentHomeworkDao) {
        this.studentHomeworkDao = studentHomeworkDao;
    }

    public List<StudentHomework> findAll(){
        return studentHomeworkDao.selectAll();
    }

    public void addStudentHomework(StudentHomework sh){
        studentHomeworkDao.addStudentHomework(sh);
    }

}
