package com.example.sys.vo;

import com.example.sys.pojo.LogLogin;
import com.example.sys.pojo.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LogLoginVo extends LogLogin {

    private static final long serialVersionUID = 1L;

    private Integer page=1;

    private Integer limit=10;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:MM:SS")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:MM:SS")
    private Date endTime;

}
