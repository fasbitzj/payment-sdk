package com.ucloudlink.api.internal.util;

import javax.net.ssl.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by jiang.zheng on 2017/2/24.
 */
public class PostJsonTest {
    private static SSLContext ctx             = null;

    private static HostnameVerifier verifier        = null;

    private static SSLSocketFactory socketFactory   = null;

    private static class DefaultTrustManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }
    }

    public static void main(String args[]) {
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() },
                    new SecureRandom());

            ctx.getClientSessionContext().setSessionTimeout(15);
            ctx.getClientSessionContext().setSessionCacheSize(1000);

            socketFactory = ctx.getSocketFactory();
        } catch (Exception e) {
            System.out.println(e);
        }

        verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return false;//默认认证不通过，进行证书校验。
            }
        };

        try {

            String json = "{\"accessToken\":\"SIGN\",\"mvnoCode\":\"GlocM\",\"notifyUrl\":\"10.1.50.159/\",\"streamNo\":\"TRADE20170224102541608685\",\"authCode\":\"56423165322\",\"currencyType\":\"CNY\",\"sign\":\"fc33458d0b722dd642dbcf3b8b1036f4\",\"authType\":\"SIGN\",\"amount\":\"0.01\",\"loginCustomerId\":\"15124531542\",\"partnerCode\":\"GlocM\",\"merchantCode\":\"01\",\"source\":\"WAVECODE\",\"orderSN\":\"D201711012252\",\"orderDesc\":\"GlocalMe WIFI\"}";

            System.out.println(json);
            // 创建url资源
            URL url = new URL("https://alipay.ukelink.com/alipay/face/ApplyPayment");
            // 建立http连接
            HttpURLConnection conn = null;
            if ("https".equals(url.getProtocol())) {
                HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
                connHttps.setSSLSocketFactory(socketFactory);
                connHttps.setHostnameVerifier(verifier);
                conn = connHttps;
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
            // 设置允许输出
            conn.setDoOutput(true);

            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
            byte[] data = json.getBytes("UTF-8");
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));

            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");


            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
//            PrintWriter out = new PrintWriter(conn.getOutputStream());
            // 写入请求的字符串
            out.write(data);
//            out.print(json);
            out.flush();
            out.close();

            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                System.out.println("连接成功");
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                String a = null;
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    a = new String(data1);
                    System.out.println(a);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                System.out.println("no++");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
