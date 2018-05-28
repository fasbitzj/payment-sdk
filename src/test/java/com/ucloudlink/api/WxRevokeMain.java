package com.ucloudlink.api;

import com.ucloudlink.api.internal.comm.StreamNoGenerator;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;
import com.ucloudlink.api.request.WxRevokeRequest;

/**
 * Created by jiang.zheng on 2017/2/28.
 */
public class WxRevokeMain {
    static String serverUrl = "https://cnweixin.ucloudlink.com";
    static String apiKey = "f199d05e0c116515c650917aeea7235b";
    //    static String serverUrl = "https://alipay.ukelink.com";
//    static String apiKey = "e0c116588af199d05aeea72358275091";
    static String partnerCode = "GlocM";
    static String mvnoCode = "GlocM";
    static String merchantCode = "01";
    static String notifyUrl = "10.1.50.159/";

    static String accessToken = "SIGN";  // 默认使用签名认证
    static String authType = "SIGN";     // 默认签名校验

    static UcloudlinkClient ucloudlinkClient = new DefaultUcloudlinkClient(serverUrl, apiKey, mvnoCode, merchantCode, partnerCode, accessToken, authType);

    public static void main(String[] args) {
        UcloudlinkLogger.logBizDebug("--- test start ---");
        UcloudlinkHashMap udfParams = new UcloudlinkHashMap();
        udfParams.put("streamNo", StreamNoGenerator.getTradeStreamNo());
        udfParams.put("orderSN", "TRADENO20161202181000477099");
        udfParams.put("loginCustomerId", "154651789423415");

        WxRevokeRequest request = new WxRevokeRequest();
        request.setUdfParams(udfParams);

        UcloudlinkResponse response = null;
        try {
            response = ucloudlinkClient.execute(request);
            UcloudlinkLogger.logBizError("--- response --------->" + response);
        } catch (UcloudlinkApiException e) {
            UcloudlinkLogger.logBizError(e.getMessage());
            UcloudlinkLogger.logBizError(e);
        }

    }
}
