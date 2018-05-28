package com.ucloudlink.api;

import java.util.Map;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public interface UcloudlinkClient {
    /**
     *
     *
     * @param <T>
     * @param request
     * @return
     * @throws UcloudlinkApiException
     */
    public <T extends UcloudlinkResponse> T execute(UcloudlinkRequest<T> request) throws UcloudlinkApiException;

    /**
     *
     * @param <T>
     * @param request
     * @return
     * @throws UcloudlinkApiException
     */
    public <T extends UcloudlinkResponse> T pageExecute(UcloudlinkRequest<T> request) throws UcloudlinkApiException;

    /**
     * SDK客户端调用生成sdk字符串
     * @param <T>
     * @param request
     * @return
     * @throws UcloudlinkApiException
     */
    public <T extends UcloudlinkResponse> T sdkExecute(UcloudlinkRequest<T> request) throws UcloudlinkApiException;

    /**
     *
     * @param request
     * @return
     * @throws UcloudlinkApiException
     */
    public <T extends UcloudlinkResponse> T pageExecute(UcloudlinkRequest<T> request,
                                                    String method) throws UcloudlinkApiException;

    /**
     * 移动客户端同步结果返回解析的参考工具方法
     *
     * @param result 移动客户端SDK同步返回的结果map，一般包含resultStatus，result和memo三个key
     * @param requsetClazz 接口请求request类，如App支付传入 UcloudlinkTradeAppPayRequest.class
     * @return 同步返回结果的response对象
     * @throws UcloudlinkApiException
     */
    public <TR extends UcloudlinkResponse, T extends UcloudlinkRequest<TR>> TR parseAppSyncResult(Map<String, String> result,
                                                                                          Class<T> requsetClazz) throws UcloudlinkApiException;
}
