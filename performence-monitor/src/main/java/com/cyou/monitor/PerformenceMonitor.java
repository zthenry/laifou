/*
 * 文 件 名:  PerformenceMonitor.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Aspect
@Component
public class PerformenceMonitor
{
    private static final Logger logger = LoggerFactory.getLogger("monitor");  
    /** 
     * 监控com.henry.advertising.web.service包及其子包的所有public方法 
     * <功能详细描述> 
     * @see [类、类#方法、类#成员] 
     */  
    @Pointcut("execution(* com.cyou.web.login..*.*(..))")    
    private void pointCutMethod() {    
    }    
  
     //声明环绕通知    
    @Around("pointCutMethod()")    
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {    
        long begin = System.nanoTime();  
        Object o = pjp.proceed();    
        long end = System.nanoTime();  
        logger.debug("{}:{}",pjp.getTarget().getClass()+"."+pjp.getSignature().getName(),(end-begin)/1000000);  
        return o;    
    }
    
}
