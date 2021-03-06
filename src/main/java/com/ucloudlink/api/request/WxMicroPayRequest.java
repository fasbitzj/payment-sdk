package com.ucloudlink.api.request;

import com.ucloudlink.api.UcloudlinkRequest;
import com.ucloudlink.api.UcloudlinkResponse;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

/**
 * 微信刷卡支付
 * Created by jiang.zheng on 2017/2/28.
 */
public class WxMicroPayRequest implements UcloudlinkRequest<UcloudlinkResponse> {

    private final String apiUri = "/weixin/Micropay";

    private UcloudlinkHashMap udfParams;

    @Override
    public String getApiUri() {
        return this.apiUri;
    }

    public UcloudlinkHashMap getUdfParams() {
        return udfParams;
    }

    public void setUdfParams(UcloudlinkHashMap udfParams) {
        this.udfParams = udfParams;
    }
}
