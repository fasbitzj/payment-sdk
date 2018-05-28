package com.ucloudlink.api.internal.comm;

import com.ucloudlink.api.UcloudlinkResponse;
import net.sf.json.JSONObject;

/**
 * Created by jiang.zheng on 2017/2/27.
 */
public class JsonObjectFormat {

    public static <T extends UcloudlinkResponse> T formatResponse(String json, Class clazz) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return (T) JSONObject.toBean(jsonObject, clazz);
    }
}
