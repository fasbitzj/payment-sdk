package com.ucloudlink.api;

/**
 * Created by jiang.zheng on 2017/2/23.
 */
public interface UcloudlinkParser<T extends UcloudlinkResponse> {

    /**
     * 把响应字符串解释成相应的领域对象。
     *
     * @param rsp 响应字符串
     * @return 领域对象
     */
    public T parse(String rsp) throws UcloudlinkApiException;
}
