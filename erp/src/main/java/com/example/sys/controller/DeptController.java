package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.common.DataGidView;
import com.example.sys.common.TreeNode;
import com.example.sys.pojo.Dept;
import com.example.sys.pojo.Notice;
import com.example.sys.service.DeptService;
import com.example.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    @RequestMapping("/loadDeptManagerLeftTreeJson")
    public DataGidView loadDeptManagerLeftTreeJson(){
        List<Dept> list = deptService.list();
        List<TreeNode> nodes = new ArrayList<>();
        for (Dept dept : list){
            Boolean spread = dept.getOpen()==1? true : false;
            nodes.add(new TreeNode(dept.getId(), dept.getPid(), dept.getTitle(),spread));
        }
        return new DataGidView(nodes);
    }

    @RequestMapping("/loadAllDept")
    public DataGidView loadAllDept(DeptVo deptVo){
        LambdaQueryWrapper<Dept> wrapper = new QueryWrapper<Dept>().lambda();
        wrapper.like(StringUtils.isNotBlank(deptVo.getTitle()), Dept::getTitle,deptVo.getTitle())
                .like(StringUtils.isNotBlank(deptVo.getAddress()),Dept::getAddress,deptVo.getAddress())
                .like(StringUtils.isNotBlank(deptVo.getRemark()), Dept::getRemark, deptVo.getRemark())
                .eq(deptVo.getId()!=null,Dept::getId,deptVo.getId()).or().eq(deptVo.getPid()!=null,
                Dept::getPid, deptVo.getPid());
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        deptService.page(page, wrapper);
    return new DataGidView(page.getTotal(), page.getRecords());
    }
}
