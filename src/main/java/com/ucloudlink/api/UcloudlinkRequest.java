package com.ucloudlink.api;

import com.ucloudlink.api.internal.util.UcloudlinkHashMap;

import java.util.Map;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public interface UcloudlinkRequest<T extends UcloudlinkResponse> {

    /**
     *
     *
     * @return
     */
    public UcloudlinkHashMap getUdfParams();

    /**
     *
     * @param udfParams
     */
    public void setUdfParams(UcloudlinkHashMap udfParams);

    /**
     *
     * @return
     */
    public String getApiUri();
}
