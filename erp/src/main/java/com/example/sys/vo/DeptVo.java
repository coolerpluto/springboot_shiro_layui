package com.example.sys.vo;

import com.example.sys.pojo.Dept;
import com.example.sys.pojo.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

    private static final long serialVersionUID = 1L;

    private Integer page=1;

    private Integer limit=10;
}
