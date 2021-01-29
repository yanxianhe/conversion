package com.yxh.conversion.ping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.yxh.conversion.tools.IDEA;
import com.yxh.conversion.tools.MyMsg;
import com.yxh.conversion.tools.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;
    /**
     * 获取服务器启动时间
     */
    @ApiOperation(value = "获取服务器启动时间")
    @ResponseBody
    @RequestMapping(value = "/queryServerStartDate", produces = {"application/json;charset=UTF-8" }, method = RequestMethod.GET)
    public String queryServerStartDate() throws Exception {
        JSONObject dataset = new JSONObject();
        
        dataset.put("msg", "queryServerStartDate " + MyUtils.getServerStartDate().toString());
        dataset.put("retAddr", request.getRemoteAddr());
        dataset.put("retUser",request.getRemoteUser());
        return MyMsg.reqSuccessMsg(dataset);
    }

    /**
     * 获取服务器DB时间
     */
    @ApiOperation(value = "获取服务器启动时间")
    @ResponseBody
    @RequestMapping(value = "/queryDBDate", produces = {"application/json;charset=UTF-8" }, method = RequestMethod.GET)
    public String queryDBDate() throws Exception {
        JSONObject dataset = new JSONObject();
        // 连接数据库获取数据时间
        // db_date = 
        // dataset.put("msg", "queryServerStartDate " + db_date);
        dataset.put("retAddr", request.getRemoteAddr());
        dataset.put("retUser",request.getRemoteUser());
        return MyMsg.reqSuccessMsg(dataset);
    }

    /**
     * ping
     */
    @ApiOperation(value = "服务探针")
    @ResponseBody
    @RequestMapping(value = "/ping", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
    public String ping() throws Exception {
        JSONObject dataset = new JSONObject();
        dataset.put("retAddr", request.getRemoteAddr());
        dataset.put("retUser",request.getRemoteUser());
        return MyMsg.reqSuccessMsg(dataset);
    }

}
