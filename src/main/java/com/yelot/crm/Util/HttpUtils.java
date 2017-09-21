package com.yelot.crm.Util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kee
 */
public class HttpUtils {


    public static void main(String[] args) {
        String url = "";
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            System.out.println("get(url) = " + post(url, nameValuePairs));
    }


    /**
     * 发送post请求
     * List<NameValuePair> nvps = new ArrayList<NameValuePair>();
     * nvps.add(new BasicNameValuePair("username", "vip"));
     * nvps.add(new BasicNameValuePair("password", "secret"));
     */
    public static String post(String url, List<NameValuePair> nameValuePairList) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        String result = null;

        CloseableHttpResponse response = null;
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "utf-8"));
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            result = parse(inputStream);

            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert response != null;
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送post请求
     */
    public static String post(String url, StringEntity stringEntity) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(stringEntity);
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            result = parse(inputStream);

            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert response != null;
            response.close();
        }
        return result;
    }

    /**
     * 将流转换为字符串
     *
     * @param inputStream
     * @return
     */
    private static String parse(InputStream inputStream) {
        String result = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = sb.toString();
        return result;
    }

}
