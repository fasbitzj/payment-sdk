package com.ucloudlink.api.internal.util;

import com.ucloudlink.api.UcloudlinkLogger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public class WebUtilsTest {

    private static String url = "https://alipay.ukelink.com/alipay/face/ApplyPayment";
    private int    connectTimeout = 3000;
    private int    readTimeout    = 15000;

    @Before
    public void init() {
        UcloudlinkLogger.logBizDebug("-------init--------");
    }

    @Test
    public void doGetTest() throws IOException {
        String url = "https://alipay.ukelink.com/";
        Map<String, String> param = new HashMap<>();
        String result = WebUtils.doGet(url, param);
        UcloudlinkLogger.logBizError(result);
    }

    @Test
    public void postJsonTest() throws IOException {
        String json = "{\"accessToken\":\"SIGN\",\"mvnoCode\":\"GlocM\",\"notifyUrl\":\"10.1.50.159/\",\"streamNo\":\"TRADE20170224102541608685\",\"authCode\":\"56423165322\",\"currencyType\":\"CNY\",\"sign\":\"fc33458d0b722dd642dbcf3b8b1036f4\",\"authType\":\"SIGN\",\"amount\":\"0.01\",\"loginCustomerId\":\"15124531542\",\"partnerCode\":\"GlocM\",\"merchantCode\":\"01\",\"source\":\"WAVECODE\",\"orderSN\":\"D201711012252\",\"orderDesc\":\"GlocalMe WIFI\"}";
        String result = WebUtils.doPostJson(url, json, "UTF-8", connectTimeout, readTimeout);
        System.out.println(result);
    }
}
