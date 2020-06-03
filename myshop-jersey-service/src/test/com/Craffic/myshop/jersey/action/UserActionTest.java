package com.Craffic.myshop.jersey.action;

import com.Craffic.myshop.jersey.Utils.HttpClientUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserActionTest {
    @Test
    public void httpClientTest(){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/services/v1/user/detail/1");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        }catch (ClientProtocolException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void httpClientUtilsTest(){
        String url = "http://localhost:8080/services/v1/user/detail";
        Map<String, String> params = new HashMap();
        params.put("userId", "1");
        String result = HttpClientUtils.doGet(url, params);
    }

}
