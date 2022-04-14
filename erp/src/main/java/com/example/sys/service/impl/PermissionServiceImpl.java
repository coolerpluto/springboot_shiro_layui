package com.example.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sys.pojo.Permission;
import com.example.sys.service.PermissionService;
import com.example.sys.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService{

    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public Permission getPermissionSelf(Integer id) {
        return permissionMapper.getPermissionSelf(id);
    }

    @Override
    public IPage<Map> getPermissionListByIPage(Page<Map> page) {
        return permissionMapper.getPermissionListByIPage(page, null);
    }
}




