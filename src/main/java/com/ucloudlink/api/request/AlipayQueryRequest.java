package com.ucloudlink.api.request;

import com.ucloudlink.api.UcloudlinkRequest;
import com.ucloudlink.api.UcloudlinkResponse;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

/**
 * 支付宝支付查询
 * Created by jiang.zheng on 2017/2/27.
 */
public class AlipayQueryRequest implements UcloudlinkRequest<UcloudlinkResponse> {

    private final String apiUri = "/alipay/face/OrderQuery";

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
