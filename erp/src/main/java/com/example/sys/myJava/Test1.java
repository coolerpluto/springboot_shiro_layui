package com.example.sys.myJava;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class Test1 {
    public static void main(String[] args) {
        String a = " ";
        System.out.println(StringUtils.isNotBlank(a));
        System.out.println(a==null);
        System.out.println();
    }

    public void sout1(){
        System.out.println("test1方法sout1执行");
    }
}
