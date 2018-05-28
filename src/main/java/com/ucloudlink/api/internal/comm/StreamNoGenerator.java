package com.ucloudlink.api.internal.comm;

import com.ucloudlink.api.UcloudlinkConstants;
import com.ucloudlink.api.UcloudlinkLogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jiang.zheng on 2017/2/23.
 */
public class StreamNoGenerator {

    private static final String TRADE_STREAM_NO_CODE = "TRADE";
    private static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     *
     * @param prefix
     * @return
     */
    public static String getTradeStreamNoCode(String prefix) {
        StringBuffer sb = new StringBuffer();
        sb.append(prefix + dateTimeFormatNow());
        sb.append(randomNumber(6));
        return sb.toString();
    }

    /**
     *
     * @return
     */
    public static String getTradeStreamNo() {
        return getTradeStreamNoCode(TRADE_STREAM_NO_CODE);
    }

    private static String dateTimeFormatNow() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone(UcloudlinkConstants.DATE_TIMEZONE));
        return sdf.format(date);
    }

    /**
     * 生成长度为length的随机字符串
     * @param length
     * @return
     */
    public static String randomNumber(int length) {
        int value = 1;
        for (int i=1; i<length; i++) {
            value = value * 10;
        }
        int d = (int) ((Math.random() * 9 + 1) * value);
        return String.valueOf(d);
    }

    public static void main(String[] args) {
        System.out.print("------- StreamNo ---------->" + getTradeStreamNo());
    }
}
