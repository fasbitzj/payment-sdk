package com.ucloudlink.api.request;

import com.ucloudlink.api.UcloudlinkRequest;
import com.ucloudlink.api.UcloudlinkResponse;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

/**
 * Created by jiang.zheng on 2017/2/28.
 */
public class WxRevokeRequest implements UcloudlinkRequest<UcloudlinkResponse> {

    private final String apiUri = "/weixin/CancelPayment";

    private UcloudlinkHashMap udfParams;

    @Override
    public UcloudlinkHashMap getUdfParams() {
        return udfParams;
    }

    @Override
    public void setUdfParams(UcloudlinkHashMap udfParams) {
        this.udfParams = udfParams;
    }

    @Override
    public String getApiUri() {
        return apiUri;
    }
}
