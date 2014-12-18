/*
 * 文 件 名:  PersonConnectionDeal.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.queue;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyou.service.api.dao.personcontact.PersonConnectionDAO;
import com.cyou.service.api.personcontact.model.PersonConnection;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class PersonConnectionDeal implements Runnable
{

    private final Logger logger = LoggerFactory.getLogger(PersonConnectionDeal.class);
    
    @Autowired
    private PersonConnectionDAO personConnectionDAO;
    @Override
    public void run()
    {
        logger.error("Thread begin");
        while (true)
        {
            Long masterId = MasterQueue.remove();
            
            List<PersonConnection> personConnections = personConnectionDAO.findSlaverIdByMasterAndRelationType(masterId, 0);
            if (personConnections!=null && personConnections.size()>0)
            {
                StringBuffer sb = new StringBuffer();
                for (PersonConnection pc : personConnections)
                {
                    
                    PersonConnection personConnection = personConnectionDAO.findbyMasterAndSlaver(masterId, pc.getSlaverPhoneId());
                    if (personConnection!=null)
                    {
                        sb.append(pc.getId()+",");
                        sb.append(personConnection.getId()+",");
                    }
                }
                sb.append("0");
                personConnectionDAO.updateRelationTypeBatch(1, sb.toString());
            }
        }
        
        
        
    }
    
}
