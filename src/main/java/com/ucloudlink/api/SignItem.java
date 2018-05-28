package com.ucloudlink.api;

import java.io.Serializable;

/**
 * 签名类型
 *
 * Created by jiang.zheng on 2017/2/24.
 */
public class SignItem implements Serializable {

    /** */
    private static final long serialVersionUID = 1L;

    /**
     * 签名源串
     */
    private String            signSourceData;

    /**
     * 签名
     */
    private String            sign;
    private String signSourceDate;


    public String getSignSourceData() {
        return signSourceData;
    }

    public void setSignSourceData(String signSourceData) {
        this.signSourceData = signSourceData;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setSignSourceDate(String signSourceDate) {
        this.signSourceDate = signSourceDate;
    }
}
