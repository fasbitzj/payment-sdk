package com.ucloudlink.api.request;

import com.ucloudlink.api.UcloudlinkRequest;
import com.ucloudlink.api.UcloudlinkResponse;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

/**
 * 微信退款
 * Created by jiang.zheng on 2017/2/28.
 */
public class WxRefundRequest implements UcloudlinkRequest<UcloudlinkResponse> {

    private final String apiUri = "/weixin/ApplyRefund";

    private UcloudlinkHashMap udfParams;

    @Override
    public UcloudlinkHashMap getUdfParams() {
        return udfParams;
    }

    @Override
    public String getApiUri() {
        return apiUri;
    }

    public void setUdfParams(UcloudlinkHashMap udfParams) {
        this.udfParams = udfParams;
    }
}
