package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.common.DataGidView;
import com.example.sys.pojo.LogLogin;
import com.example.sys.service.LogLoginService;
import com.example.sys.vo.LogLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("logLogin")
@Controller
public class LogLoginController {

    @Autowired
    LogLoginService logLoginService;

    public DataGidView loadAllLogLogin(LogLoginVo logLoginVo){

        IPage<LogLogin> page = new Page<>(logLoginVo.getPage(), logLoginVo.getLimit());
        QueryWrapper<LogLogin> wrapper = new QueryWrapper();
        wrapper.like(StringUtils.isNotBlank(logLoginVo.getLoginname()),"loginname", logLoginVo.getLoginname())
                .like(StringUtils.isNotBlank(logLoginVo.getLoginip()), "loginip",logLoginVo.getLoginip())
                .ge(logLoginVo.getLogintime()!=null,"logintime",logLoginVo.getLogintime())
                .le(logLoginVo.getLogintime()!=null, "logintime", logLoginVo.getLogintime());
        this.logLoginService.page(page, wrapper);
        return new DataGidView(page.getTotal(), page.getRecords());
    }
}
