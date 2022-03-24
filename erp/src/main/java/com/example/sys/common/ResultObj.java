package com.example.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultObj {


    public static final ResultObj LOGIN_SUCCESS = new ResultObj(Constast.OK,"登录成功");
    public static final ResultObj LOGIN_ERROR_PASS = new ResultObj(Constast.ERROR,"用户名或码不正确");
    public static final ResultObj LOGIN_ERROR_CODE = new ResultObj(Constast.ERROR, "验证码不正确");

    private Integer code;
    private String msg;
}
