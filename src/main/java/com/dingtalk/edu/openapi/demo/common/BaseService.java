package com.dingtalk.edu.openapi.demo.common;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiServiceGetCorpTokenRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiServiceGetCorpTokenResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.dingtalk.edu.openapi.demo.config.ApiUrlConstant;
import com.dingtalk.edu.openapi.demo.config.Constant;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Service;

/**
 * @author ***
 * @date 2020/09/04
 */
@Service
public class BaseService {

    /**
     * suiteTicket是一个定时变化的票据，主要目的是为了开发者的应用与钉钉之间访问时的安全加固。
     * 测试应用：可随意设置，钉钉只做签名不做安全加固限制。
     * 正式应用：开发者应该从自己的db中读取suiteTicket,suiteTicket是由开发者在开发者平台设置的应用回调地址，由钉钉定时推送给应用，
     * 由开发者在回调地址所在代码解密和验证签名完成后获取到的.正式应用钉钉会在开发者代码访问时做严格检查。
     *
     * @return suiteTicket
     */
    private String getSuiteTicket(String suiteKey) {
        //正式应用必须由应用回调地址从钉钉推送获取
        return "temp_suite_ticket_only4_test";
    }

    /**
     * 获取accessToken
     * @param corpId
     * @return
     */
    public String getAccessToken(String corpId) {
        if (corpId == null || corpId.isEmpty()) {
            return null;
        }
        DefaultDingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_GET_CORP_TOKEN);
        OapiServiceGetCorpTokenRequest req = new OapiServiceGetCorpTokenRequest();
        //授权企业的corpId
        req.setAuthCorpid(corpId);
        OapiServiceGetCorpTokenResponse rsp;
        try {
            rsp = client.execute(req, Constant.SUITE_KEY, Constant.SUITE_SECRET, getSuiteTicket(Constant.SUITE_KEY));
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        if (rsp == null || !rsp.isSuccess()) {
            return null;
        }
        return rsp.getAccessToken();
    }

    /**
     * 获取用户id
     * @param corpId 企业id
     * @param authLoginCode 免登授权码
     * @return
     */
    public String getUserId(String corpId, String authLoginCode) {
        String accessToken = getAccessToken(corpId);
        OapiUserGetuserinfoResponse rsp = getUserInfo(accessToken, authLoginCode);
        if (rsp == null || !rsp.isSuccess()) {
            return null;
        }
        return rsp.getUserid();
    }

    /**
     * 获取用户信息
     * @param accessToken 授权token
     * @param authLoginCode 免登授权码
     * @return
     */
    public OapiUserGetuserinfoResponse getUserInfo(String accessToken, String authLoginCode) {
        DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(authLoginCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse rsp;
        try {
            rsp = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        if (rsp == null || !rsp.isSuccess()) {
            return null;
        }
        return rsp;
    }
}
