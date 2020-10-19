package com.dingtalk.edu.openapi.demo.config;

/**
 * @author ***
 * @date 2020/09/07
 */
public class ApiUrlConstant {
    /**
     * 获取可访问企业相关信息的accessToken的URL
     */
    public static final String URL_GET_CORP_TOKEN = "https://oapi.dingtalk.com/service/get_corp_token";

    /**
     * 获取用户在企业内userId的接口URL
     */
    public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";

    /**
     * 创建课程接口的URL
     */
    public static final String URL_CREATE_COURSE = "https://oapi.dingtalk.com/topapi/edu/course/create";

    /**
     * 更新课程接口的URL
     */
    public static final String URL_UPDATE_COURSE = "https://oapi.dingtalk.com/topapi/edu/course/update";

    /**
     * 删除课程接口的URL
     */
    public static final String URL_DELETE_COURSE = "https://oapi.dingtalk.com/topapi/edu/course/delete";

    /**
     * 获取课程列表接口的URL
     */
    public static final String URL_FIND_COURSE_LIST = "https://oapi.dingtalk.com/topapi/edu/course/list";

    /**
     * 获取课程详情接口的URL
     */
    public static final String URL_GET_COURSE_DETAIL = "https://oapi.dingtalk.com/topapi/edu/course/get";

    /**
     * 开始课程接口的URL
     */
    public static final String URL_START_COURSE = "https://oapi.dingtalk.com/topapi/edu/course/start";

    /**
     * 加入课程接口的URL
     */
    public static final String URL_JOIN_COURSE = "https://oapi.dingtalk.com/topapi/edu/course/join";

    /**
     * 结束课程接口的URL
     */
    public static final String URL_END_COURSE = "https://oapi.dingtalk.com/topapi/edu/course/end";

    /**
     * 为课程新增参与方接口的URL
     */
    public static final String URL_ADD_PARTICIPANT = "https://oapi.dingtalk.com/topapi/edu/course/participant/add";

    /**
     * 为课程移除参与方接口的URL
     */
    public static final String URL_REMOVE_PARTICIPANT = "https://oapi.dingtalk.com/topapi/edu/course/participant/remove";

    /**
     * 查询课程参与方列表接口的URL
     */
    public static final String URL_FIND_PARTICIPANT_LIST = "https://oapi.dingtalk.com/topapi/edu/course/participant/list";

    /**
     * 创建学科接口的URL
     */
    public static final String URL_CREATE_SUBJECT = "https://oapi.dingtalk.com/topapi/edu/subject/create";

    /**
     * 更新学科接口的URL
     */
    public static final String URL_UPDATE_SUBJECT = "https://oapi.dingtalk.com/topapi/edu/subject/update";

    /**
     * 删除学科接口的URL
     */
    public static final String URL_DELETE_SUBJECT = "https://oapi.dingtalk.com/topapi/edu/subject/delete";

    /**
     * 获取学科列表接口的URL
     */
    public static final String URL_FIND_SUBJECT_LIST = "https://oapi.dingtalk.com/topapi/edu/subject/list";

    /**
     * 获取学科详情接口的URL
     */
    public static final String URL_GET_SUBJECT_DETAIL ="https://oapi.dingtalk.com/topapi/edu/subject/get";
}
