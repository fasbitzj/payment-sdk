package com.ucloudlink.api.internal.util;

import com.ucloudlink.api.UcloudlinkConstants;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by jiang.zheng on 2017/2/23.
 */
public class UcloudlinkHashMap extends HashMap {

    public UcloudlinkHashMap() {
        super();
    }

    public UcloudlinkHashMap(Map m) {
        super(m);
    }

    /**
     * 类型转换
     * @param key
     * @param value
     * @return
     */
    public Object put(String key, Object value) {
        String strValue = null;
        if (value instanceof Date) {
            DateFormat format = new SimpleDateFormat(UcloudlinkConstants.DATE_TIME_FORMAT);
            format.setTimeZone(TimeZone.getTimeZone(UcloudlinkConstants.DATE_TIMEZONE));
            strValue = format.format((Date) value);
            return this.put(key, strValue);
        }
        return super.put(key, value);
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public Object put(String key, String value) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
            return super.put(key, value);
        }
        return null;
    }
}
