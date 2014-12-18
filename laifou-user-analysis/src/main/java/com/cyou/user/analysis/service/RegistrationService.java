package com.cyou.user.analysis.service;

import java.util.Date;
import java.util.List;

import com.cyou.user.analysis.model.RegisterPhone;
import com.cyou.util.Pagination;

public interface RegistrationService
{
    /**
     * total count of register
     * @return
     * @see [类、类#方法、类#成员]
     */
    Long countAll();
    
    /**
     * 查询所有注册用户
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<RegisterPhone> queryAllRegisterPhone();
    
    /**
     * 按照手机号 ，注册时间范围查询
     * <功能详细描述>
     * @param phone
     * @param begin
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<RegisterPhone> queryRegisterPhoneByPage(String phone,Date begin,Date end,Pagination page);
}
