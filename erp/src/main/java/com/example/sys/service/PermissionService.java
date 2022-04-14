package com.example.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface PermissionService extends IService<Permission> {
    Permission getPermissionSelf(Integer id);

    IPage<Map> getPermissionListByIPage(Page<Map> page);
}
