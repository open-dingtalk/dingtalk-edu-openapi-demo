package com.dingtalk.edu.openapi.demo.controller.course;

import javax.annotation.Resource;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiEduCourseCreateRequest;
import com.dingtalk.api.request.OapiEduCourseDeleteRequest;
import com.dingtalk.api.request.OapiEduCourseEndRequest;
import com.dingtalk.api.request.OapiEduCourseGetRequest;
import com.dingtalk.api.request.OapiEduCourseJoinRequest;
import com.dingtalk.api.request.OapiEduCourseListRequest;
import com.dingtalk.api.request.OapiEduCourseParticipantAddRequest;
import com.dingtalk.api.request.OapiEduCourseParticipantListRequest;
import com.dingtalk.api.request.OapiEduCourseParticipantRemoveRequest;
import com.dingtalk.api.request.OapiEduCourseStartRequest;
import com.dingtalk.api.request.OapiEduCourseUpdateRequest;
import com.dingtalk.api.response.OapiEduCourseCreateResponse;
import com.dingtalk.api.response.OapiEduCourseDeleteResponse;
import com.dingtalk.api.response.OapiEduCourseEndResponse;
import com.dingtalk.api.response.OapiEduCourseGetResponse;
import com.dingtalk.api.response.OapiEduCourseJoinResponse;
import com.dingtalk.api.response.OapiEduCourseListResponse;
import com.dingtalk.api.response.OapiEduCourseParticipantAddResponse;
import com.dingtalk.api.response.OapiEduCourseParticipantListResponse;
import com.dingtalk.api.response.OapiEduCourseParticipantRemoveResponse;
import com.dingtalk.api.response.OapiEduCourseStartResponse;
import com.dingtalk.api.response.OapiEduCourseUpdateResponse;
import com.dingtalk.edu.openapi.demo.common.BaseService;
import com.dingtalk.edu.openapi.demo.config.ApiUrlConstant;
import com.dingtalk.edu.openapi.demo.model.request.CourseParticipantWriteRequest;
import com.dingtalk.edu.openapi.demo.model.request.EduCourseWriteRequest;
import com.taobao.api.ApiException;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/course")
public class CourseController {
    @Resource
    private BaseService baseService;

    /**
     * 节点在主流程位置：「创建课程」--> 查询课程列表 --> 查询课程详情 --> 订阅课程 --> 开始课程 --> 进入课程 --> 结束课程
     * <p>
     * 在线授课前，需要先创建一个课程； 正式上课时，老师基于这个课程发起直播； 未完结的课程可以支持多次发起直播；
     *
     * @param request 创建课程请求对象
     * @return
     */
    @PostMapping("/create")
    public String createCourse(@RequestBody EduCourseWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_CREATE_COURSE);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseCreateRequest req = new OapiEduCourseCreateRequest();
        req.setOpUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));
        req.setTeacherCorpid(request.getTeacherCorpid());
        req.setTeacherUserid(request.getTeacherUserid());
        req.setIntroduce(request.getIntroduce());
        //TODO 关联课程的业务唯一key, 开发者要自己实现
        req.setBizKey("bizKey_course_001");
        req.setName(request.getName());
        req.setStartTime(request.getStartTime());
        req.setEndTime(request.getEndTime());
        OapiEduCourseCreateResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(request.getAuthCorpId()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }

        //body实例："{course_code: "testCode001",errcode: 0,errmsg: "ok",success: true,request_id: "112321"}"
        //TODO 其中课程编码：course_code: "EKKKe168004",要保存留作后续修改课程相关信息时使用
        return rsp.getBody();
    }

    /**
     * 节点在主流程位置：创建课程 -->「查询课程列表」 --> 查询课程详情 --> 订阅/取消订阅 --> 开始课程 --> 加入课程 --> 结束课程
     * <p>
     * 查看课程列表，支持分页查询 家长订阅前会先查询课程列表
     *
     * @param corpId 企业id
     * @param authLoginCode 免登码
     * @return
     */
    @GetMapping("/findList")
    public String findCourseList(@RequestParam String corpId, @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_FIND_COURSE_LIST);
        OapiEduCourseListRequest req = new OapiEduCourseListRequest();
        //TODO 参数校验逻辑开发者要自己实现
        //该opUserId主要是用于识别谁查询了课程列表
        req.setOpUserid(baseService.getUserId(corpId, authLoginCode));
        req.setCursor(0L);
        req.setSize(10L);
        OapiEduCourseListResponse rsp = null;
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

    /**
     * 节点在主流程位置：创建课程 --> 查询课程列表 --> 「查询课程详情」 --> 订阅/取消订阅 --> 开始课程 --> 加入课程 --> 结束课程
     * <p>
     * 查询课程详情，用于获取上课时间、授课老师、课程简介等关键信息
     *
     * @param corpId  企业id
     * @param courseCode    课程编码
     * @param authLoginCode 免登码
     * @return
     */
    @GetMapping("/get")
    public String getCourse(@RequestParam String corpId, @RequestParam String courseCode, @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_GET_COURSE_DETAIL);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseGetRequest req = new OapiEduCourseGetRequest();
        req.setCourseCode(courseCode);
        req.setOpUserid(baseService.getUserId(corpId, authLoginCode));
        OapiEduCourseGetResponse rsp = null;
        //可以封装一个切面，统一做try catch处理
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

    /**
     * 节点在主流程位置：创建课程 --> 查询课程列表 --> 查询课程详情 --> 「订阅课程」 --> 开始课程 --> 加入课程 --> 结束课程
     * <p>
     * 学生订阅课程对应的底层逻辑是：针对某个课程添加一个学生身份的课程参与方
     *
     * @param request 课程参与方对象
     * @return
     */
    @PostMapping("/participant/add")
    public String addParticipant(@RequestBody CourseParticipantWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_ADD_PARTICIPANT);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseParticipantAddRequest req = new OapiEduCourseParticipantAddRequest();
        req.setOpUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));

        req.setCourseCode(request.getCourseCode());
        req.setParticipantCorpid(request.getParticipantCorpId());
        req.setParticipantId(request.getParticipantId());
        req.setRole(request.getRole());
        // 1:用户; 2:部门; 3:组织
        req.setParticipantType(request.getParticipantType());
        OapiEduCourseParticipantAddResponse rsp = null;
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
     * 节点在主流程位置：创建课程 --> 查询课程列表 --> 查询课程详情 --> 「取消订阅」 --> 开始课程 --> 加入课程 --> 结束课程
     * <p>
     * 学生取消已订阅课程对应的底层逻辑是：针对某个课程移除一个学生身份的课程参与方
     *
     * @param request 课程参与方对象
     * @return
     */
    @PostMapping("/participant/remove")
    public String removeParticipant(@RequestBody CourseParticipantWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_REMOVE_PARTICIPANT);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseParticipantRemoveRequest req = new OapiEduCourseParticipantRemoveRequest();
        req.setOpUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));
        req.setParticipantCorpid(request.getParticipantCorpId());
        req.setCourseCode(request.getCourseCode());
        req.setParticipantId(request.getParticipantId());
        req.setRole(request.getRole());
        req.setParticipantType(request.getParticipantType());
        OapiEduCourseParticipantRemoveResponse rsp = null;
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
     * 节点在主流程位置：创建课程 --> 查询课程列表 --> 查询课程详情 --> 订阅课程 --> 「开始课程」 --> 加入课程 --> 结束课程
     * <p>
     * 开始课程后，就开始进入上课环节，老师可以多次发起直播（技术实现方式:jsapi 唤起）
     * 本次调用是获取课程相关的target_id，后续就可以通过前端代码用jsapi的方式实现唤起直播的逻辑；
     *
     * @param corpId  操作人所属企业id
     * @param courseCode    课程编码
     * @param authLoginCode 免登码
     * @return
     */
    @GetMapping("/start")
    public String startCourse(@RequestParam String corpId, @RequestParam String courseCode, @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_START_COURSE);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseStartRequest req = new OapiEduCourseStartRequest();
        req.setCourseCode(courseCode);
        req.setOpUserId(baseService.getUserId(corpId, authLoginCode));

        OapiEduCourseStartResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(corpId));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }
        //body样例："{"errcode":0,"errmsg":"ok","result":{"is_reuse":false,"target_id":"targetIdTest_01","target_type":2},"success":true,"request_id":"12001"}"
        //TODO 其中的target_id 是通过jsapi唤起直播时用的参数，要开发者保存，以便老师发起直播时使用；
        return rsp.getBody();
    }

    /**
     * 节点在主流程位置：创建课程 --> 查询课程列表 --> 查询课程详情 --> 订阅课程 --> 开始课程 --> 「加入课程」 --> 结束课程
     * <p>
     * 学生订阅课程，会生成一个joinUrl用于后续学生在开直播课时，通过点击课表链接到该joinUrl去加入直播
     *
     * @param corpId 操作人所属企业id
     * @param courseCode 课程编码
     * @param authLoginCode 免登码
     * @return
     */
    @GetMapping("/join")
    public String joinCourse(@RequestParam String corpId, @RequestParam String courseCode,
                             @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_JOIN_COURSE);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseJoinRequest req = new OapiEduCourseJoinRequest();
        req.setCourseCode(courseCode);
        req.setOpUserId(baseService.getUserId(corpId, authLoginCode));
        OapiEduCourseJoinResponse rsp = null;
        try {
            rsp = client.execute(req, baseService.getAccessToken(corpId));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (rsp == null) {
            return null;
        }
        //body示例："{errcode: 0,errmsg: "ok",result: {join_url: "https://h5.dingtalk.com/group-live-share/index.htm?type=3&liveUuid=test001&roomId=roomTest001#/union",joinable: true},success: true,request_id: "test001"}"
        //TODO 其中join_url 就是作为加入直播的链接，开发者要保存以便后续学生通过课表进入直播使用；
        return rsp.getBody();
    }

    /**
     * 节点在主流程位置：创建课程 --> 查询课程列表 --> 查询课程详情 --> 订阅课程 --> 开始课程 --> 加入课程 --> 「结束课程」
     * <p>
     * 结束课程（同步关闭直播，后续无法再通过该课程启动直播）
     *
     * @param corpId  操作人所属企业id
     * @param courseCode 课程编码
     * @param authLoginCode 免登码
     * @return
     */
    @GetMapping("/end")
    public String endCourse(@RequestParam String corpId, @RequestParam String courseCode,
                            @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_END_COURSE);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseEndRequest req = new OapiEduCourseEndRequest();
        req.setCourseCode(courseCode);
        req.setOpUserId(baseService.getUserId(corpId, authLoginCode));
        OapiEduCourseEndResponse rsp = null;
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

    /**
     * 查询一个课程对应的课程参与方列表（如课程关联的老师、学生列表）
     *
     * @param corpId  操作人所属企业id
     * @param courseCode 课程编码
     * @param authLoginCode 免登码
     * @return
     */
    @GetMapping("/participant/list")
    public String findParticipantList(@RequestParam String corpId, @RequestParam String courseCode,
                                      @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_FIND_PARTICIPANT_LIST);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseParticipantListRequest req = new OapiEduCourseParticipantListRequest();
        req.setCourseCode(courseCode);
        req.setOpUserid(baseService.getUserId(corpId, authLoginCode));
        req.setCursor(0L);
        req.setSize(10L);
        OapiEduCourseParticipantListResponse rsp = null;
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

    /**
     * 更新课程（更新课程的授课老师、介绍、起止时间等相关信息）
     *
     * @param request  课程对象,其中的courseCode 就是创建课程时返回的courseCode
     * @return
     */
    @PostMapping("/update")
    public String updateCourse(@RequestBody EduCourseWriteRequest request) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_UPDATE_COURSE);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseUpdateRequest req = new OapiEduCourseUpdateRequest();
        req.setCourseCode(request.getCourseCode());
        req.setTeacherCorpid(request.getTeacherCorpid());
        req.setTeacherUserid(request.getTeacherUserid());
        req.setIntroduce(request.getIntroduce());
        BeanUtils.copyProperties(request, req);
        req.setOpUserid(baseService.getUserId(request.getAuthCorpId(), request.getAuthLoginCode()));
        OapiEduCourseUpdateResponse rsp = null;
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
     * 删除课程（课程取消了，管理员操作取消课程）
     *
     * @param corpId  企业id
     * @param courseCode    课程编码
     * @param authLoginCode 免登码
     * @return
     */
    @GetMapping("/delete")
    public String deleteCourse(@RequestParam String corpId, @RequestParam String courseCode, @RequestParam String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_DELETE_COURSE);
        //TODO 参数校验逻辑开发者要自己实现
        OapiEduCourseDeleteRequest req = new OapiEduCourseDeleteRequest();
        req.setCourseCode(courseCode);
        req.setOpUserid(baseService.getUserId(corpId, authLoginCode));
        OapiEduCourseDeleteResponse rsp = null;
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