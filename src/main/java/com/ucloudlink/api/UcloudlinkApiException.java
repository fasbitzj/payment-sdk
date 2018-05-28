package com.ucloudlink.api;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public class UcloudlinkApiException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String      errCode;
    private String      errMsg;

    public UcloudlinkApiException() {
        super();
    }

    public UcloudlinkApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public UcloudlinkApiException(String message) {
        super(message);
    }

    public UcloudlinkApiException(Throwable cause) {
        super(cause);
    }

    public UcloudlinkApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
