/*
 * 文 件 名:  RegisterServiceImpl.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.user.analysis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyou.user.analysis.dao.RegisterPhoneDAO;
import com.cyou.user.analysis.model.RegisterPhone;
import com.cyou.user.analysis.service.RegistrationService;
import com.cyou.util.Pagination;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService
{

    @Autowired
    private RegisterPhoneDAO registerPhoneDAO;
    @Override
    public List<RegisterPhone> queryAllRegisterPhone()
    {
        return registerPhoneDAO.findALLRegister();
    }

    @Override
    public List<RegisterPhone> queryRegisterPhoneByPage(String phone, Date begin, Date end,Pagination page)
    {
        
        return registerPhoneDAO.findByPage(phone, begin, end, page);
    }

    @Override
    public Long countAll()
    {
        return registerPhoneDAO.countALLRegister();
    }

    
}
