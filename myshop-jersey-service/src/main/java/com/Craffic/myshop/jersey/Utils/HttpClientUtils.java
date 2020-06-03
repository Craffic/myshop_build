package com.Craffic.myshop.jersey.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";
    // 采用静态代码块，初始化超时时间配置，再根据配置生成默认httpClient对象
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, CHARSET);
    }

//    public static String doGetSSL(String url, Map<String, String> params) {
//        return doGetSSL(url, params, CHARSET);
//    }
//
//    public static String doPost(String url, Map<String, String> params) throws IOException {
//        return doPost(url, params, CHARSET);
//    }

    public static String doGet(String url, Map<String, String> params, String charSet){

        if (StringUtils.isEmpty(url)){
            throw new RuntimeException("url is not allowed empty......");
        }
        // 设置参数
        if (!CollectionUtils.isEmpty(params)){
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                url = url + "/" +value;
            }

            // 将请求参数和url进行拼接
            try {
                //url += "/" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    httpGet.abort();
                    throw new RuntimeException("HttpClient,error status code :" + statusCode);
                }
                HttpEntity entity = response.getEntity();
                String result = null;
                if (entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                }
                EntityUtils.consume(entity);
                response.close();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
