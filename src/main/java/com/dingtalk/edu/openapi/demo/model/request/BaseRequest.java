package com.dingtalk.edu.openapi.demo.model.request;

import java.io.Serializable;

/**
 * @author ***
 * @date 2020/09/07
 */
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = -5916003176417033935L;
    /**
     * 操作人所属企业id
     */
    private String authCorpId;

    /**
     * 免登授权码
     */
    private String authLoginCode;

    public String getAuthCorpId() {
        return authCorpId;
    }

    public void setAuthCorpId(String authCorpId) {
        this.authCorpId = authCorpId;
    }

    public String getAuthLoginCode() {
        return authLoginCode;
    }

    public void setAuthLoginCode(String authLoginCode) {
        this.authLoginCode = authLoginCode;
    }
}
