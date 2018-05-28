package com.ucloudlink.api.request;

import com.ucloudlink.api.UcloudlinkRequest;
import com.ucloudlink.api.UcloudlinkResponse;
import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

/**
 * 支付宝条码支付
 * Created by jiang.zheng on 2017/2/23.
 */
public class AlipayBarcodePayRequest implements UcloudlinkRequest<UcloudlinkResponse> {

    private final String apiUri = "/alipay/face/ApplyPayment";

    private UcloudlinkHashMap udfParams;

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
