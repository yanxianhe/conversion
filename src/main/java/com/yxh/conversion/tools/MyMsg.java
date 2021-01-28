package com.yxh.conversion.tools;

import com.alibaba.fastjson.JSONObject;

public class MyMsg {
    /*
     * 拼装成功数据 <p>拼装数据</p>
     */
    public static String reqSuccessMsg(JSONObject dataset) {
        JSONObject json = new JSONObject();
        json.put("msg", TransactionCode.SYS_MSG);
        json.put("code", TransactionCode.SYS_SUCCESS_CODE);

        if(!(dataset == null || dataset.isEmpty() || "null".equals(dataset))) {
            json.put("data", dataset);
        }
        return json.toJSONString();
    }

    /* 
     * 拼装失败数据
     * <p>拼装数据</p>
     */
    public static String reqFailureMsg(JSONObject dataset){
        JSONObject json = new JSONObject();
        json.put("msg", TransactionCode.SYS_FAILURE_MSG);
        json.put("code", TransactionCode.SYS_FAILURE_MSG);
        
        if(!(dataset == null || dataset.isEmpty() || "null".equals(dataset))) {
            json.put("data", dataset);
        }
        return json.toJSONString();
    }

}
