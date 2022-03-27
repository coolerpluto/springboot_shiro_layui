package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.common.*;
import com.example.sys.pojo.Permission;
import com.example.sys.pojo.User;
import com.example.sys.service.PermissionService;
import com.example.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("menu")
@RestController
public class MenuController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("loadIndexLeftMenu")
    public DataGidView loadIndexLeftMenu(PermissionVo permissionVo){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("type", Constast.TYPE_MENU)
                .eq("available",Constast.AVAILABLE_TRUE);
        List<Permission> list = null;
        User user = (User) WebUtils.getSession().getAttribute("user");
        if (user.getType() == Constast.USER_TYPE_SUPER){
            list = permissionService.list(wrapper);
        }else {
            list = permissionService.list(wrapper);
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : list) {
            Integer id = permission.getId();
            Integer pid = permission.getPid();
            String title = permission.getTitle();
            String icon = permission.getIcon();
            String href = permission.getHref();
            Boolean spread = permission.getOpen() == 1?true:false;
            treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
        }
        List<TreeNode> treeNodeList = TreeNodeBuilder.treeNodesBuild(treeNodes, 1);
        return new DataGidView(treeNodeList);

    }
}
