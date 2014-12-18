/*
 * 文 件 名:  RegisterServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.register.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cyou.service.api.dao.register.RegisterPhoneDAO;
import com.cyou.service.api.register.RegisterService;
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
public class RegisterServiceImpl implements RegisterService
{

    @Autowired
    private RegisterPhoneDAO registerPhoneDAO;
    @Override
    public boolean register(String phoneNumber, String verificationCode)
    {
        if(verificationCode!=null && verificationCode.trim().length()==6 && phoneNumber!=null && CommonUtils.isMobilePhoneNum(phoneNumber.trim())){
            RegisterPhone registerPhone = new RegisterPhone();
            registerPhone.setPhone(phoneNumber);
            registerPhone.setStatus(1);
            registerPhoneDAO.insertForRegist(registerPhone);
            return true;
        }
        
        return false;
       
    }

    
    
}
