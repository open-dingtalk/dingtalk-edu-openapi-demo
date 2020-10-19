package com.dingtalk.edu.openapi.demo.controller.subject;

import javax.annotation.Resource;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiEduSubjectCreateRequest;
import com.dingtalk.api.request.OapiEduSubjectDeleteRequest;
import com.dingtalk.api.request.OapiEduSubjectGetRequest;
import com.dingtalk.api.request.OapiEduSubjectListRequest;
import com.dingtalk.api.request.OapiEduSubjectUpdateRequest;
import com.dingtalk.api.response.OapiEduSubjectCreateResponse;
import com.dingtalk.api.response.OapiEduSubjectDeleteResponse;
import com.dingtalk.api.response.OapiEduSubjectGetResponse;
import com.dingtalk.api.response.OapiEduSubjectListResponse;
import com.dingtalk.api.response.OapiEduSubjectUpdateResponse;
import com.dingtalk.edu.openapi.demo.common.BaseService;
import com.dingtalk.edu.openapi.demo.config.ApiUrlConstant;
import com.dingtalk.edu.openapi.demo.model.request.EduSubjectWriteRequest;
import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ***
 * @date 2020/09/03
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Resource
    private BaseService baseService;

    /**
     * 创建学科
     * @param request 学科对象
     * @return
     */
    @PostMapping("/create")
    public String createSubject(@RequestBody EduSubjectWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_CREATE_SUBJECT);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduSubjectCreateRequest req = new OapiEduSubjectCreateRequest();
        req.setCode(request.getCode());
        req.setName(request.getName());
        req.setOperatorUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));
        OapiEduSubjectCreateResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(request.getAuthCorpId()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }
        return rsp.getBody();
    }

    /**
     * 更新学科（更新名称）
     * @param request 学科对象
     * @return
     */
    @PostMapping("/update")
    public String updateSubject(@RequestBody EduSubjectWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_UPDATE_SUBJECT);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduSubjectUpdateRequest req = new OapiEduSubjectUpdateRequest();
        req.setCode(request.getCode());
        req.setName(request.getName());
        req.setOperatorUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));
        OapiEduSubjectUpdateResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(request.getAuthCorpId()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }
        return rsp.getBody();
    }

    /**
     * 删除学科
     * @param request 学科对象
     * @return
     */
    @PostMapping("/delete")
    public String deleteSubject(@RequestBody EduSubjectWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_DELETE_SUBJECT);
        //TODO 参数校验逻辑开发者要自己实现

        OapiEduSubjectDeleteRequest req = new OapiEduSubjectDeleteRequest();
        req.setCode(request.getCode());
        req.setName(request.getName());
        req.setOperatorUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));
        OapiEduSubjectDeleteResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(request.getAuthCorpId()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }
        return rsp.getBody();
    }

    /**
     * 查询学科详情
     * @param request 查询对象
     * @return
     */
    @PostMapping("/get")
    public String getSubject(@RequestBody EduSubjectWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_GET_SUBJECT_DETAIL);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduSubjectGetRequest req = new OapiEduSubjectGetRequest();
        req.setCode(request.getCode());
        req.setName(request.getName());
        req.setOperatorUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));
        OapiEduSubjectGetResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(request.getAuthCorpId()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }
        return rsp.getBody();
    }

    /**
     * 查询学科列表
     * @param corpId 操作人所属企业id
     * @param authLoginCode 免登授权码
     * @return
     */
    @GetMapping("/list")
    public String findList(@RequestParam String corpId, @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_FIND_SUBJECT_LIST);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduSubjectListRequest req = new OapiEduSubjectListRequest();
        req.setOperatorUserid(baseService.getUserId(corpId, authLoginCode));
        OapiEduSubjectListResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(corpId));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }
        return rsp.getBody();
    }
}
