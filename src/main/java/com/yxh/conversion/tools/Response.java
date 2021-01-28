package com.yxh.conversion.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Response {

    private HttpsURLConnection https;
    private HttpURLConnection http;
    private int status;
    private InputStream is;
    private String responseAsString = null;
    private boolean streamConsumed = false;

    public Response() {
    }

    public Response(HttpsURLConnection https) throws IOException {
        this.https = https;
        this.status = https.getResponseCode();
        if (null == (is = https.getErrorStream())) {
            is = https.getInputStream();
        }
    }

    public Response(HttpURLConnection http) throws IOException {
        this.http = http;
        this.status = http.getResponseCode();
        if (null == (is = http.getErrorStream())) {
            is = http.getInputStream();
        }
    }

    /**
     * 转换为输出流
     *
     * @return 输出流
     */
    public InputStream asStream() {
        if (streamConsumed) {
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return is;
    }

    /**
     * 将输出流转换为String字符串
     *
     * @return 输出内容
     * @throws Exception 操作异常
     */
    public String asString() throws Exception {
        if (null == responseAsString) {
            BufferedReader br;
            try {
                InputStream stream = asStream();
                if (null == stream) {
                    return null;
                }
                br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
                StringBuilder buf = new StringBuilder();
                String line;
                while (null != (line = br.readLine())) {
                    buf.append(line).append("\n");
                }
                this.responseAsString = buf.toString();
                stream.close();
                //输出流读取完毕，关闭连接
                if (https != null) {
                    https.disconnect();
                }
                //输出流读取完毕，关闭连接
                if (http != null) {
                    http.disconnect();
                }
                streamConsumed = true;
            } catch (NullPointerException npe) {
                // don't remember in which case npe can be thrown
                throw new Exception(npe.getMessage(), npe);
            } catch (IOException ioe) {
                throw new Exception(ioe.getMessage(), ioe);
            }
        }
        return responseAsString;
    }

    /**
     * 将输出流转换为JSON对象
     *
     * @return JSONObject对象
     * @throws Exception 操作异常
     */
    public JSONObject asJSONObject() throws Exception {
        return JSONObject.parseObject(asString());
    }

    /**
     * 将输出流转换为JSON对象
     *
     * @return JSONArray对象
     * @throws Exception 操作异常
     */
    public JSONArray asJSONArray() throws Exception {
        return JSONArray.parseArray(asString());
    }

    /**
     * 获取响应状态
     *
     * @return 响应状态
     */
    public int getStatus() {
        return status;
    }

    /**
     * 将InputStream流转换为JSON对象
     *
     * @return JSONObject对象
     * @throws Exception 操作异常
     */
    public static JSONObject inJSONObject(InputStream stream) throws Exception {

        BufferedReader br;
        String inString;
        if (null == stream) {
            return null;
        }
        try {
            br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            StringBuilder buf = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                buf.append(line).append("\n");
            }
            inString = buf.toString();
            stream.close();
        } catch (NullPointerException npe) {
            // don't remember in which case npe can be thrown
            throw new Exception(npe.getMessage(), npe);
        } catch (IOException ioe) {
            throw new Exception(ioe.getMessage(), ioe);
        }
        return JSONObject.parseObject(inString);
    }
}

