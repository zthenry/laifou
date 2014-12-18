/*
 * 文 件 名:  RedisInitTask.java
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-8-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.queue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class ServiceProviderInit
{
    
    private final Logger logger = LoggerFactory.getLogger(ServiceProviderInit.class);
    
    @Autowired
    private PersonConnectionDeal personConnectionDeal;
   
    @PostConstruct
    public void initBlockingQueue(){

    	//初始化
    	logger.error("系统启动，初始化开始......");
        Thread thread = new Thread(personConnectionDeal);
        thread.start();
    }
    
    
}
