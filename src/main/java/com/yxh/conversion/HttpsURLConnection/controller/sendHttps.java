package com.yxh.conversion.HttpsURLConnection.controller;


import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import com.alibaba.fastjson.JSONObject;
import com.yxh.conversion.tools.MyConstants;
import com.yxh.conversion.tools.Response;
import com.yxh.conversion.tools.SSLUtils;
public class sendHttps {
    public static void main(String[] args) throws Exception {
        HttpURLConnection connection = null;
        String rest_urls = "https://127.0.0.1/8080/api";

        URL url = new URL(rest_urls);
        try {
            connection = (HttpURLConnection) url.openConnection(); // 创建一个HTTP连接
            if (url.getProtocol().equalsIgnoreCase("https")) {
                SSLUtils.trustAll((HttpsURLConnection) connection);
            }
            connection.setRequestMethod("GET"); // 指定使用GET请求方式
            connection.setDoInput(true); // 向连接中写入数据
            connection.setDoOutput(true); // 从连接中读取数据
            connection.setUseCaches(false); // 禁止缓存
            connection.setInstanceFollowRedirects(true); // 自动执行HTTP重定向
            connection.setRequestProperty("Authorization", MyConstants.AUTHORIZATION); // 设置认证信息

            // 更多返回代码参见帮助

            if (connection.getResponseCode() == 200) { // 判断是否响应成功
                JSONObject json = Response.inJSONObject(connection.getInputStream());
                System.out.println("JSONObject " + json);
            } else {
                System.out.println("Failed to retrieve object! " + connection.getResponseCode() + " " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect(); // 断开连接
                connection = null;
            }
        }

        try {
            connection = (HttpURLConnection) url.openConnection(); // 创建一个HTTP连接

            if (url.getProtocol().equalsIgnoreCase("https")) {
                SSLUtils.trustAll((HttpsURLConnection) connection);
            }

            connection.setRequestMethod("DELETE"); // 指定使用GET请求方式
            connection.setDoInput(true); // 向连接中写入数据
            connection.setDoOutput(true); // 从连接中读取数据
            connection.setUseCaches(false); // 禁止缓存
            connection.setInstanceFollowRedirects(true); // 自动执行HTTP重定向
            connection.setRequestProperty("Authorization", MyConstants.AUTHORIZATION); // 设置认证信息
            if (connection.getResponseCode() == 200) { // 判断是否响应成功
                System.out.println("Object deleted!");
            } else {
                System.out.println("Failed to deleted object! " + connection.getResponseCode() + " " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect(); // 断开连接
                connection = null;
            }
        }
    }

}
