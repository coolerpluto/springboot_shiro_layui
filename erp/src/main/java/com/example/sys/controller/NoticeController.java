package com.example.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sys.common.DataGidView;
import com.example.sys.common.ResultObj;
import com.example.sys.common.WebUtils;
import com.example.sys.pojo.Notice;
import com.example.sys.pojo.User;
import com.example.sys.service.NoticeService;
import com.example.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /**
     * 加载所有公告
     * @param noticeVo
     * @return
     */
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

    /**
     * 新增公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("/addNotice")
    public ResultObj addNotice(NoticeVo noticeVo){
        try {
            Notice notice = new Notice();
            notice.setTitle(noticeVo.getTitle());
            notice.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            notice.setOpername(user.getName());
            noticeService.save(notice);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_PASS;
        }
    }

    /**
     * 删除公告
     * @return
     */
    @RequestMapping("/deleteNotice")
    public ResultObj deleteNotice(Integer id){
        try {
            noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_PASS;
        }
    }

    /**
     * 批量删除
     * @param noticeVo
     * @return
     */
    @RequestMapping("/batchDeleteNotice")
    public ResultObj batchDeleteNotice(NoticeVo noticeVo){
        try {
            Collection<Serializable> ids = new ArrayList<Serializable>();
            for (Integer id : noticeVo.getIds()){
                ids.add(id);
            }
            noticeService.removeByIds(ids);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_PASS;
        }
    }

    /**
     * 修改公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("/updateNotice")
    public ResultObj updateNotice(NoticeVo noticeVo){
        try {
            noticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_PASS;
        }
    }
}
