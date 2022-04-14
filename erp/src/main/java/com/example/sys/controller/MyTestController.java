package com.example.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.pojo.Permission;
import com.example.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class MyTestController {

    @Autowired
    PermissionService permissionService;

    @ResponseBody
    @RequestMapping("/test01")
    public Permission permissionSelfSelect(@RequestParam("id") Integer id){
        return permissionService.getPermissionSelf(id);
    }

    @ResponseBody
    @RequestMapping("/test02")
    public IPage<Map> getPermissionListByIPage(){
        Integer currentPage = 1;
        Integer pageSize = 10;
        Page<Map> page = new Page<>(currentPage,pageSize);
        return permissionService.getPermissionListByIPage(page);
    }
}
