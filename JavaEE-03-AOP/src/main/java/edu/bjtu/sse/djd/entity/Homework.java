package edu.bjtu.sse.djd.entity;

import java.util.Date;

public class Homework {

    private Long id;

    private String homeworkTittle;

    private String homeworkContent;

    private Date createTime;

    private Date updateTime;

    public Homework(String homeworkTittle, String homeworkContent) {
        this.homeworkTittle = homeworkTittle;
        this.homeworkContent = homeworkContent;
    }

    public Homework(Long id, String homeworkTittle, String homeworkContent) {
        this.id = id;
        this.homeworkTittle = homeworkTittle;
        this.homeworkContent = homeworkContent;
    }

    public Homework(Long id, String homeworkTittle, String homeworkContent, Date createTime, Date updateTime) {
        this.id = id;
        this.homeworkTittle = homeworkTittle;
        this.homeworkContent = homeworkContent;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHomeworkTittle() {
        return homeworkTittle;
    }

    public void setHomeworkTittle(String homeworkTittle) {
        this.homeworkTittle = homeworkTittle;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", homeworkTittle='" + homeworkTittle + '\'' +
                ", homeworkContent='" + homeworkContent + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
