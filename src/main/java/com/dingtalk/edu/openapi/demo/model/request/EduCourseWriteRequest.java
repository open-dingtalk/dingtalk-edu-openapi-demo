package com.dingtalk.edu.openapi.demo.model.request;

import java.io.Serializable;

/**
 * @author ***
 * @date 2020/09/03
 */
public class EduCourseWriteRequest extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 7921388289358889549L;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程编码
     */
    private String courseCode;
    /**
     * 课程介绍
     */
    private String introduce;
    /**
     * 用户id
     */
    private String opUserid;
    /**
     * 课程开始时间
     */
    private Long startTime;
    /**
     * 课程结束时间
     */
    private Long endTime;
    /**
     * 授课老师所属企业id
     */
    private String teacherCorpid;
    /**
     * 授课老师在所属企业的userid
     */
    private String teacherUserid;
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getOpUserid() {
        return opUserid;
    }

    public void setOpUserid(String opUserid) {
        this.opUserid = opUserid;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getTeacherCorpid() {
        return teacherCorpid;
    }

    public void setTeacherCorpid(String teacherCorpid) {
        this.teacherCorpid = teacherCorpid;
    }

    public String getTeacherUserid() {
        return teacherUserid;
    }

    public void setTeacherUserid(String teacherUserid) {
        this.teacherUserid = teacherUserid;
    }
}
