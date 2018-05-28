package com.ucloudlink.api.internal.util;

import com.ucloudlink.api.UcloudlinkLogger;
import com.ucloudlink.api.UcloudlinkResponse;
import org.apache.commons.lang.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by jiang.zheng on 2017/2/23.
 */
public class UcloudlinkSignature {

    private static final String ALGORITHM = "MD5";
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 回签
     * @param tRsp
     * @return
     */
    public static String getSignatureContentByResponse(UcloudlinkResponse tRsp) {
        Map<String, Object> map = new TreeMap<>();
        if (null != tRsp.getData()) {
            map.putAll(tRsp.getData());
        }
        map.put("streamNo", tRsp.getStreamNo());
        map.put("resultCode", tRsp.getResultCode());
        map.put("resultDesc", tRsp.getResultDesc());
        return getSignContent(map);
    }

    public static String getSignatureContent(RequestParametersHolder requestHolder, String apiKey) {
        return getSignContent(getSortedMap(requestHolder)) + "&key=" + apiKey;
    }

    public static Map<String, Object> getSortedMap(RequestParametersHolder requestHolder) {
        Map<String, Object> sortedParams = new TreeMap<>();
        UcloudlinkHashMap appParams = requestHolder.getApplicationParams();
        if (appParams != null && appParams.size() > 0) {
            sortedParams.putAll(appParams);
        }
        UcloudlinkHashMap protocolMustParams = requestHolder.getProtocalMustParams();
        if (protocolMustParams != null && protocolMustParams.size() > 0) {
            sortedParams.putAll(protocolMustParams);
        }
        UcloudlinkHashMap protocalOptParams = requestHolder.getProtocolOptParams();
        if (protocalOptParams != null && protocalOptParams.size() > 0) {
            sortedParams.putAll(protocalOptParams);
        }

        return sortedParams;
    }

    /**
     *
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, Object> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = sortedParams.get(key);
            if (null != value && StringUtils.isNotBlank(value + "")) {
                content.append((index==0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * Hashing for String
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encryptMD5(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
            md5.update(text.getBytes(DEFAULT_CHARSET));

            return encodeHex(md5.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            UcloudlinkLogger.logBizError(e);
        }
        return null;
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static String encodeHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
