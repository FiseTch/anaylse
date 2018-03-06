package tch.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tch.util.ExcelUtil;
/*
@Controller
@RequestMapping("/ExcelDownload")
public class ExcelDownload extends BaseController{
	
	private static Log log = LogFactory.getLog(ExcelDownload.class);
    
    @Resource BrokerInfoService brokerInfoService;
    @Resource CustomerService customerService;
    
    @RequestMapping(value = "/download/{name}.do",method = RequestMethod.POST)
    public void getSystemExcel(HttpServletRequest request, HttpServletResponse response, 
            @PathVariable("name") String name) {
        try {
            if("recommend".equals(name)){//推荐导出
                ExcelUtil.downloadExcel(request,response,ExcelExportEnum.recommend, getRecommendMap(), getRecommendData(request));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
    private List<CustomerPo> getRecommendData(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String projectName = request.getParameter("projectName");
        if(StringUtils.isNotBlank(name))
            map.put("name", name);
        if(StringUtils.isNotBlank(city))
            map.put("city", city);
        if(StringUtils.isNotBlank(projectName))
            map.put("projectName", projectName);
        return customerService.findCustomerList(map, 1);
    }

    private LinkedHashMap<String, String> getRecommendMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("客户姓名", "name");
        map.put("手机号", "tel");
        map.put("性别", "sex");
        map.put("预约城市", "city");
        map.put("预约项目", "projectName");
        map.put("意向产品类型", "productTypeName");
        map.put("客户备注信息", "remarks");
        map.put("跟进状态", "status");
        map.put("最近跟进时间", "followUp");
        map.put("经纪人姓名", "brokerName");
        map.put("经纪人手机号", "brokerTel");
        map.put("已结佣金", "brokerAmount");
        return map;
    }
}*/