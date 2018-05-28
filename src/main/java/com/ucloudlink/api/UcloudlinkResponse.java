package com.ucloudlink.api;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public class UcloudlinkResponse implements Serializable {

    private static final long   serialVersionUID = 5014379068811962022L;

    private String          streamNo;

    private String          sign;

    private String          resultCode;

    private String          resultDesc;

    private String body;

    private Map<String, Object> data;

    public String getStreamNo() {
        return streamNo;
    }

    public void setStreamNo(String streamNo) {
        this.streamNo = streamNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return StringUtils.isEmpty(resultCode);
    }

    @Override
    public String toString() {
        return "UcloudlinkResponse{" +
                "streamNo='" + streamNo + '\'' +
                ", sign='" + sign + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", resultDesc='" + resultDesc + '\'' +
                ", data=" + data +
                '}';
    }
}
