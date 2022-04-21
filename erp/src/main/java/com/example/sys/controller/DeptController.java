package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.common.DataGidView;
import com.example.sys.common.ResultObj;
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

import java.util.*;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    /**
     * 加载部门管理左侧部门树
     * @return
     */
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

    /**
     * 获取所有部门信息
     * @param deptVo
     * @return
     */
    @RequestMapping("/loadAllDept")
    public DataGidView loadAllDept(DeptVo deptVo){
        LambdaQueryWrapper<Dept> wrapper = new QueryWrapper<Dept>().lambda();
        wrapper.like(StringUtils.isNotBlank(deptVo.getTitle()), Dept::getTitle,deptVo.getTitle())
                .like(StringUtils.isNotBlank(deptVo.getAddress()),Dept::getAddress,deptVo.getAddress())
                .like(StringUtils.isNotBlank(deptVo.getRemark()), Dept::getRemark, deptVo.getRemark())
                .eq(deptVo.getId()!=null,Dept::getId,deptVo.getId()).or().eq(deptVo.getPid()!=null,
                Dept::getPid, deptVo.getPid())
                .orderByAsc(Dept::getOrdernum);
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        deptService.page(page, wrapper);
    return new DataGidView(page.getTotal(), page.getRecords());
    }

    /**
     * 获取部门表里最大的排序值，方便添加部门时不用手动填写排序码
     * @return
     */
    @RequestMapping("/loadDeptMaxOrderNum")
    public Map<String,Object> loadDeptMaxOrderNum(){
        Map<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<Dept> wrapper = new QueryWrapper<Dept>().lambda();
        wrapper.orderByDesc(Dept::getOrdernum);
        List<Dept> list = deptService.list(wrapper);
        if(list.size()>0){
            map.put("value", list.get(0).getOrdernum()+1);
        }else{
            map.put("value",1);
        }
        return map;
    }

    /**
     * 添加部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/addDept")
    public ResultObj addDept(DeptVo deptVo){
        try {
            deptVo.setCreatetime(new Date());
            deptService.save(deptVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_PASS;
        }
    }

    /**
     * 修改部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/updateDept")
    public ResultObj updateDept(DeptVo deptVo){
        try {
            deptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_PASS;
        }
    }

    /**
     * 判断当前节点部门是否有子部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/checkDeptHasChildrenNode")
    public Map<String, Object> checkDeptHasChildrenNode(DeptVo deptVo){
        Map<String,Object> map= new HashMap<>();
        LambdaQueryWrapper<Dept> wrapper =new LambdaQueryWrapper<Dept>()
                .eq(Dept::getPid,deptVo.getPid());
        List<Dept> list = deptService.list(wrapper);
        if (list.size() > 0){
            map.put("value", true);
        }else {
            map.put("value", true);
        }
        return map;
    }

    /**
     * 删除部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/deleteDept")
    public ResultObj deleteDept(DeptVo deptVo){
        try {
            deptService.removeById(deptVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_PASS;
        }
    }
}
