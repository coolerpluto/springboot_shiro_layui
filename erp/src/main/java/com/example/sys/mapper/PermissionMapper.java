package com.example.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.example.sys.pojo.Permission
 */

public interface PermissionMapper extends BaseMapper<Permission> {
    Permission getPermissionSelf(Integer id);

    @MapKey("id")
    IPage<Map> getPermissionListByIPage(Page<Map> page, QueryWrapper<Map<String, Object>> wrapper);
}




