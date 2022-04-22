package com.example.sys.cache;

import com.example.sys.pojo.Dept;
import com.example.sys.vo.DeptVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {
//    定义缓存容器
    private Map<String, Object> CACHE_CONTAINER = new HashMap<>();

//    定义部门缓存前缀
    private static final String DEPT_PREFIX = "dept:";

//    切面表达式写法：必要元素包括返回类型和方法包路径，"*"代表任意字符，"."代表当前包下，".."如果是在路径里不在方法括号里代表的当前包下及其子包，
//    方法括号里的".."代表任意参数个数

//    定义切面表达式
    private static final String  POINTCUT_DEPT_GET = "execution(* com.example.sys.service.impl.DeptServiceImpl.getById(..))";
    private static final String  POINTCUT_DEPT_DELETE = "execution(* com.example.sys.service.impl.DeptServiceImpl.removeById(..))";
    private static final String  POINTCUT_DEPT_UPDATE = "execution(* com.example.sys.service.impl.DeptServiceImpl.updateById(..))";

    /**
     * 查询部门缓存
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {

        Integer deptId = (Integer) joinPoint.getArgs()[0];
        Object res = CACHE_CONTAINER.get(DEPT_PREFIX+deptId);
        if (res==null){
            Dept dept = (Dept) joinPoint.proceed();
            CACHE_CONTAINER.put(DEPT_PREFIX+deptId,dept);
            return dept;
        }else {
            return res;
        }
    }

    /**
     * 删除部门缓存
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(POINTCUT_DEPT_DELETE)
    public boolean cacheDeptDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            Integer deptId = (Integer) joinPoint.getArgs()[0];
            CACHE_CONTAINER.remove(DEPT_PREFIX+deptId);
        }
        return res;
    }

    /**
     * 更新部门缓存
     * @param joinPoint
     * @return
     */
    @Around(POINTCUT_DEPT_UPDATE)
    public boolean cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        DeptVo deptVo = (DeptVo) joinPoint.getArgs()[0];
        boolean res = (Boolean) joinPoint.proceed();
        if (res){
            Dept dept = (Dept) CACHE_CONTAINER.get(DEPT_PREFIX + deptVo.getId());
            if (dept==null){
                dept = new Dept();
            }
            BeanUtils.copyProperties(deptVo, dept);
            CACHE_CONTAINER.put(DEPT_PREFIX+dept.getId(),dept);
        }
        return res;
    }
}
