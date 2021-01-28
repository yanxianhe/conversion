package com.yxh.conversion.ping.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yxh.conversion.tools.MyMsg;
import com.yxh.conversion.tools.MyUtils;
import com.yxh.conversion.tools.TransactionCode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 描述
 *
 * @author yxh
 * @date 2021/1/26
 */
@Api(tags = "系统查询服务")
@Controller
@RequestMapping("/api")
public class queryController {

    /**
     * 获取服务器启动时间
    */
    @ApiOperation(value = "获取服务器启动时间")
    @ResponseBody
    @RequestMapping(value = "/queryServerStartDate",produces = { "application/json;charset=UTF-8" },method = RequestMethod.GET)
    public String queryServerStartDate() throws Exception {
        JSONObject dataset = new JSONObject();
        dataset.put("msg", "queryServerStartDate " + MyUtils.getServerStartDate().toString());

        return MyMsg.reqSuccessMsg(dataset);
    }

    /**
     * ping
    */
    @ApiOperation(value = "服务探针")
    @ResponseBody
    @RequestMapping(value = "/ping",produces = { "application/json;charset=UTF-8" },method = RequestMethod.GET)
    public String ping() throws Exception {
        
        return MyMsg.reqSuccessMsg(null);
    }
    
}
