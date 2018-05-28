package com.ucloudlink.api;

import com.ucloudlink.api.internal.comm.JsonObjectFormat;
import com.ucloudlink.api.internal.util.RequestParametersHolder;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;
import com.ucloudlink.api.internal.util.UcloudlinkSignature;
import com.ucloudlink.api.internal.util.WebUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public class DefaultUcloudlinkClient implements UcloudlinkClient {
    private String serverUrl;
    private String apiKey;
    private String mvnoCode;
    private String merchantCode;
    private String partnerCode;
    private String format         = UcloudlinkConstants.FORMAT_JSON;

    private String accessToken = "SIGN";
    private String authType = "SIGN";
    private String currencyType = "CNY";

    private String charset;

    private int    connectTimeout = 3000;
    private int    readTimeout    = 15000;

    static {
        //清除安全设置
        Security.setProperty("jdk.certpath.disabledAlgorithms", "");
    }

    public DefaultUcloudlinkClient(String serverUrl, String apiKey, String mvnoCode, String merchantCode, String partnerCode) {
        this.serverUrl = serverUrl;
        this.apiKey = apiKey;
        this.mvnoCode = mvnoCode;
        this.merchantCode = merchantCode;
        this.partnerCode = partnerCode;
    }

    public DefaultUcloudlinkClient(String serverUrl, String apiKey, String mvnoCode, String merchantCode, String partnerCode, String accessToken, String authType) {
        this.serverUrl = serverUrl;
        this.apiKey = apiKey;
        this.mvnoCode = mvnoCode;
        this.merchantCode = merchantCode;
        this.partnerCode = partnerCode;
        this.accessToken = accessToken;
        this.authType = authType;
    }


    @Override
    public <T extends UcloudlinkResponse> T execute(UcloudlinkRequest<T> request) throws UcloudlinkApiException {
        return _execute(request);
    }

    @Override
    public <T extends UcloudlinkResponse> T pageExecute(UcloudlinkRequest<T> request) throws UcloudlinkApiException {
        return null;
    }

    @Override
    public <T extends UcloudlinkResponse> T sdkExecute(UcloudlinkRequest<T> request) throws UcloudlinkApiException {
        return null;
    }

    @Override
    public <T extends UcloudlinkResponse> T pageExecute(UcloudlinkRequest<T> request, String method) throws UcloudlinkApiException {
        return null;
    }

    @Override
    public <TR extends UcloudlinkResponse, T extends UcloudlinkRequest<TR>> TR parseAppSyncResult(Map<String, String> result, Class<T> requsetClazz) throws UcloudlinkApiException {
        return null;
    }

    /**
     *
     * @param request
     * @param <T>
     * @return
     * @throws UcloudlinkApiException
     */
    private <T extends UcloudlinkResponse> T _execute(UcloudlinkRequest<T> request) throws UcloudlinkApiException {
        Map<String, Object> rt = doPost(request);
        if (rt == null) {
            return null;
        }
        T tRsp = null;
        try {
            String rsp = (String) rt.get("rsp");
            tRsp = JsonObjectFormat.formatResponse(rsp, UcloudlinkResponse.class);

            // 验签是对请求返回原始串
            Boolean flag = checkResponseSign(tRsp);

            if (!tRsp.isSuccess()) {
                UcloudlinkLogger.logErrorScene(rt, tRsp, "");
            }
            if (!flag) {
                UcloudlinkLogger.logErrorScene(rt, tRsp, "验证签名错误");
            }
        } catch (RuntimeException e) {
            UcloudlinkLogger.logBizError((String) rt.get("rsp"));
            throw e;
        }
        return tRsp;
    }

    private boolean checkResponseSign(UcloudlinkResponse tRsp) {
        return UcloudlinkSignature.getSignatureContentByResponse(tRsp).equals(tRsp.getSign());
    }

    /**
     *
     * @param request
     * @param <T>
     * @return
     */
    private <T extends UcloudlinkResponse> Map<String, Object> doPost(UcloudlinkRequest<T> request) throws UcloudlinkApiException {
        Map<String, Object> result = new HashMap<>();
        RequestParametersHolder requestHolder = getRequestHolderWithSign(request);
        String rsp = null;
        String url = serverUrl + request.getApiUri();
        try {
            Map<String, String> requestParam = new HashedMap();
            requestParam.putAll(requestHolder.getApplicationParams());
            requestParam.putAll(requestHolder.getProtocalMustParams());
            requestParam.putAll(requestHolder.getProtocolOptParams());
            JSONObject json = JSONObject.fromObject(requestParam);
            rsp = WebUtils.doPostJson(url, json.toString(), charset,
                    connectTimeout, readTimeout);
        } catch (IOException e) {
            throw new UcloudlinkApiException(e);
        }
        result.put("rsp", rsp);
        result.put("url", url);
        return result;
    }

    /**
     * 组装参数，添加签文
     * @param request
     * @param <T>
     * @return
     */
    private <T extends UcloudlinkResponse> RequestParametersHolder getRequestHolderWithSign(UcloudlinkRequest<T> request) {
        RequestParametersHolder requestHolder = new RequestParametersHolder();
        UcloudlinkHashMap appParams = new UcloudlinkHashMap(request.getUdfParams());
        requestHolder.setApplicationParams(appParams);

        if (StringUtils.isEmpty(charset)) {
            charset = UcloudlinkConstants.CHARSET_UTF8;
        }

        UcloudlinkHashMap protocolMustParams = new UcloudlinkHashMap();
        protocolMustParams.put(UcloudlinkConstants.PARTNER_CODE, this.partnerCode);
        protocolMustParams.put(UcloudlinkConstants.MERCHANT_CODE, this.merchantCode);
        protocolMustParams.put(UcloudlinkConstants.MVNO_CODE, this.mvnoCode);

        UcloudlinkHashMap protocolOptParams = new UcloudlinkHashMap();
        protocolOptParams.put(UcloudlinkConstants.AUTH_TYPE, this.authType);
        protocolOptParams.put(UcloudlinkConstants.ACCESS_TOKEN, this.accessToken);
        requestHolder.setProtocolOptParams(protocolOptParams);

        requestHolder.setProtocalMustParams(protocolMustParams);
        String signContent = UcloudlinkSignature.getSignatureContent(requestHolder, apiKey);
        UcloudlinkLogger.logBizError("signContent ------> " + signContent);
        protocolMustParams.put(UcloudlinkConstants.SIGN, UcloudlinkSignature.encryptMD5(signContent));

        requestHolder.setProtocalMustParams(protocolMustParams);
        return requestHolder;
    }


}
