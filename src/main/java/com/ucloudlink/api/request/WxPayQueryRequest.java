package com.ucloudlink.api.request;

import com.ucloudlink.api.UcloudlinkRequest;
import com.ucloudlink.api.UcloudlinkResponse;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

/**
 * 微信支付查询
 * Created by jiang.zheng on 2017/2/28.
 */
public class WxPayQueryRequest implements UcloudlinkRequest<UcloudlinkResponse> {

    private final String apiUri = "/weixin/OrderQuery";

    private UcloudlinkHashMap udfParams;

    @Override
    public UcloudlinkHashMap getUdfParams() {
        return udfParams;
    }

    @Override
    public String getApiUri() {
        return this.apiUri;
    }

    public void setUdfParams(UcloudlinkHashMap udfParams) {
        this.udfParams = udfParams;
    }
}
