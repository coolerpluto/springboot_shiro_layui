package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.common.DataGidView;
import com.example.sys.pojo.Notice;
import com.example.sys.service.NoticeService;
import com.example.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping("/loadAllNotice")
    public DataGidView loadAllNotice(NoticeVo noticeVo){
        IPage<Notice> page = new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        LambdaQueryWrapper<Notice> wrapper = new QueryWrapper<Notice>().lambda();
        wrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),Notice::getTitle,noticeVo.getTitle())
                .like(StringUtils.isNotBlank(noticeVo.getOpername()),Notice::getOpername,noticeVo.getOpername())
                .ge(noticeVo.getCreatetime()!=null,Notice::getCreatetime, noticeVo.getCreatetime())
                .le(noticeVo.getCreatetime()!=null, Notice::getCreatetime, noticeVo.getCreatetime());
        noticeService.page(page, wrapper);
        return new DataGidView(page.getTotal(),page.getRecords());
    }
}
