package com.example.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.pojo.LogLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.example.sys.pojo.LogLogin
 */

public interface LogLoginMapper extends BaseMapper<LogLogin> {
    @MapKey("id")
    IPage<Map> selectLogLoginPage(Page<Map> page, QueryWrapper<List<Map<String, Object>>> wrapper);
}




