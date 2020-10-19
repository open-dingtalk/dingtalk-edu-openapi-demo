package com.dingtalk.edu.openapi.demo.model.request;

import java.io.Serializable;

/**
 * @author ***
 * @date 2020/09/07
 */
public class CourseParticipantWriteRequest extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 4240979586526181919L;

    /**
     * 课程编码
     */
    private String courseCode;

    /**
     * 参与方所属企业Id
     */
    private String participantCorpId;

    /**
     * 参与方id
     */
    private String participantId;

    /**
     * 参与方角色
     */
    private String role;

    /**
     * 参与方类别
     */
    private Long participantType;

    public void setParticipantType(Long participantType) {
        this.participantType = participantType;
    }

    public Long getParticipantType() {
        return this.participantType;
    }

    public String getParticipantCorpId() {
        return participantCorpId;
    }

    public void setParticipantCorpId(String participantCorpId) {
        this.participantCorpId = participantCorpId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
