package com.ucloudlink.api.internal.util;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public class RequestParametersHolder {

    /**
     * 协议必传参数
     */
    private UcloudlinkHashMap protocalMustParams;

    /**
     * 协议选择参数
     */
    private UcloudlinkHashMap protocolOptParams;

    /**
     * 应用参数
     */
    private UcloudlinkHashMap applicationParams;


    public UcloudlinkHashMap getProtocalMustParams() {
        return protocalMustParams;
    }

    public void setProtocalMustParams(UcloudlinkHashMap protocalMustParams) {
        this.protocalMustParams = protocalMustParams;
    }

    public UcloudlinkHashMap getProtocolOptParams() {
        return protocolOptParams;
    }

    public void setProtocolOptParams(UcloudlinkHashMap protocolOptParams) {
        this.protocolOptParams = protocolOptParams;
    }

    public UcloudlinkHashMap getApplicationParams() {
        return applicationParams;
    }

    public void setApplicationParams(UcloudlinkHashMap applicationParams) {
        this.applicationParams = applicationParams;
    }
}
