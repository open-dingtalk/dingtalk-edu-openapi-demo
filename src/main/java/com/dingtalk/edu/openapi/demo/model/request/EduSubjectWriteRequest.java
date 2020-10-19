package com.dingtalk.edu.openapi.demo.model.request;

import java.io.Serializable;

/**
 * @author ***
 * @date 2020/09/04
 */
public class EduSubjectWriteRequest extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 7921388289358889549L;

    /**
     * 学科名称
     */
    private String name;

    /**
     * 学科编码
     */
    private String code;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
