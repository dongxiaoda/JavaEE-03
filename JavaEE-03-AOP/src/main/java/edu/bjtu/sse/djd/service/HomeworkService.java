package edu.bjtu.sse.djd.service;

import edu.bjtu.sse.djd.dao.HomeworkDao;
import edu.bjtu.sse.djd.entity.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName HomeworkService
 * @Description TODO
 * @Author 董金达
 * @Date 2020/4/22 21:29
 * @Version 1.0
 **/

@Service
public class HomeworkService {

    private HomeworkDao homeworkDao;

    @Autowired
    public HomeworkService(HomeworkDao homeworkDao) {
        this.homeworkDao = homeworkDao;
    }

    public List<Homework> findAll(){
       return homeworkDao.selectAll();
    }

    public void addHomework(Homework h){
        homeworkDao.addHomework(h);
    }

    public void updateHomework(Homework h){
        homeworkDao.updateHomework(h);
    }

}
