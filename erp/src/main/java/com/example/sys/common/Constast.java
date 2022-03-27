package com.example.sys.common;

public interface Constast {
    /*
    状态码
     */
    public static final Integer OK = 200;
    public static  final Integer ERROR = -1;

    /*
    菜单、权限
     */
    public static final String TYPE_MENU = "menu";
    public static final String TYPE_PERMISSION = "permission";

    /*
    菜单权限是否可用
     */
    public static final Integer AVAILABLE_TRUE = 1;
    public static final Integer AVAILABLE_FALSE = 0;


    /*
    用户身份
     */
    public static final Integer USER_TYPE_SUPER = 0;
    public static final Integer USER_TYPE_NORMAL = 1;
}
