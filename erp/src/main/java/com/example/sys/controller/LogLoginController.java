package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.common.DataGidView;
import com.example.sys.common.ResultObj;
import com.example.sys.pojo.LogLogin;
import com.example.sys.service.LogLoginService;
import com.example.sys.vo.LogLoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Slf4j
@RequestMapping("/logLogin")
@Controller
public class LogLoginController {

    @Autowired
    LogLoginService logLoginService;

    /**
     * 全查询
     * @param logLoginVo
     * @return
     */
    @ResponseBody
    @RequestMapping("loadAllLogLogin")
    public DataGidView loadAllLogLogin(LogLoginVo logLoginVo){
        log.info("进入日志查询");
        IPage<LogLogin> page = new Page<>(logLoginVo.getPage(), logLoginVo.getLimit());
        QueryWrapper<LogLogin> wrapper = new QueryWrapper();
        wrapper.like(StringUtils.isNotBlank(logLoginVo.getLoginname()),"loginname", logLoginVo.getLoginname())
                .like(StringUtils.isNotBlank(logLoginVo.getLoginip()), "loginip",logLoginVo.getLoginip())
                .ge(logLoginVo.getLogintime()!=null,"logintime",logLoginVo.getLogintime())
                .le(logLoginVo.getLogintime()!=null, "logintime", logLoginVo.getLogintime());
        logLoginService.page(page, wrapper);
        return new DataGidView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("deleteLogById")
    public ResultObj deleteLogById(Integer id){
        try {
            logLoginService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_PASS;
        }
    }

    /**
     * 批量删除
     */
    @ResponseBody
    @RequestMapping("deleteLogsByIds")
    public ResultObj deleteLogsByIds(LogLoginVo logLoginVo){
        try {
            Collection<Serializable> idList=new ArrayList<Serializable>();
            for (Integer id : logLoginVo.getIds()){
                idList.add(id);
            }
            logLoginService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_PASS;
        }
    }

    @ResponseBody
    @RequestMapping("/test/jsonFormat")
    public List<LogLogin> getLogLoginTestJsonFormat(){
        return logLoginService.list();
    }
}
