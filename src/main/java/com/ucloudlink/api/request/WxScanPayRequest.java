package com.ucloudlink.api.request;

import com.ucloudlink.api.UcloudlinkRequest;
import com.ucloudlink.api.UcloudlinkResponse;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

/**
 * Created by jiang.zheng on 2017/2/28.
 */
public class WxScanPayRequest implements UcloudlinkRequest<UcloudlinkResponse> {

    private final String apiUri = "/weixin/GetWeixinCredential";

    private UcloudlinkHashMap udfParams;

    @Override
    public UcloudlinkHashMap getUdfParams() {
        return udfParams;
    }

    public void setUdfParams(UcloudlinkHashMap udfParams) {
        this.udfParams = udfParams;
    }

    @Override
    public String getApiUri() {
        return apiUri;
    }
}
