/*
 * 文 件 名:  JobMain.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.laifou.job;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class JobMain
{
    public static void main(String[] args)
    {
        new ClassPathXmlApplicationContext("applicationContext-job.xml").start();
    }
}
