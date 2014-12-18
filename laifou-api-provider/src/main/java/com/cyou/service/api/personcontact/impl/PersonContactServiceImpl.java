/*
 * 文 件 名:  PersonContactServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.personcontact.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyou.service.api.dao.personcontact.PersonConnectionDAO;
import com.cyou.service.api.dao.register.RegisterPhoneDAO;
import com.cyou.service.api.personcontact.PersonContactService;
import com.cyou.service.api.personcontact.model.PersonConnection;
import com.cyou.service.api.queue.MasterQueue;
import com.cyou.service.api.register.model.RegisterPhone;
import com.cyou.util.CommonUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("personContactService")
public class PersonContactServiceImpl implements PersonContactService
{

    @Autowired
    private RegisterPhoneDAO registerPhoneDAO;
    
    @Autowired
    private PersonConnectionDAO personConnectionDAO;
    @Override
    public String importContacts(String phoneNumber, String contacts) throws InterruptedException
    {
        if (contacts!=null && !contacts.equals(""))
        {
            List<String> importPhoneNums = new ArrayList<String>();
            String[] contactArray = contacts.split(",");
            for (String phone : contactArray)
            {
                if (CommonUtils.isMobilePhoneNum(phone.trim()))
                {
                    importPhoneNums.add(phone.trim());
                }
            }
            if (importPhoneNums.size()==0)
            {
                
            }else {
                Long masterId = registerPhoneDAO.selectByPhone(phoneNumber);
                for (String phone : importPhoneNums)
                {
                    RegisterPhone registerPhone = new RegisterPhone();
                    registerPhone.setPhone(phone);
                    registerPhone.setStatus(0);
                    registerPhoneDAO.insertForImport(registerPhone);
                    Long slaverId = registerPhone.getId();
                    PersonConnection personConnection = new PersonConnection();
                    personConnection.setMasterPhoneId(masterId);
                    personConnection.setSlaverPhoneId(slaverId);
                    personConnection.setRelationType(0);
                    personConnection.setCreateTime(new Date());
                    personConnectionDAO.insert(personConnection);
                }
                MasterQueue.add(masterId);
                
            }
        }
        return null;
    }

    @Override
    public boolean calculateSecondDimension(String phoneNumber) throws InterruptedException
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean calculateFirstDimensionByPhoneNumber(String phoneNumber) throws InterruptedException
    {
        Long masterPhoneId = registerPhoneDAO.selectByPhone(phoneNumber);
        MasterQueue.add(masterPhoneId);
        return true;
    }

    @Override
    public boolean calculateFirstDimensionByPhoneId(Long phoneId)
        throws InterruptedException
    {
        registerPhoneDAO.selectById(phoneId);
        MasterQueue.add(phoneId);
        return true;
    }
   
    
}
