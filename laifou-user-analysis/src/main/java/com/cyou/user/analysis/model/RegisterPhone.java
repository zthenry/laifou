/*
 * 文 件 名:  RegisterPhone.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.user.analysis.model;

import java.util.Date;

/**
 * 注册用户
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RegisterPhone
{
    private Long id;
    
    private String phone;
    
    private Date registTime;
    
    private Integer status;

    /**
     * @return 返回 id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return 返回 phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @param 对phone进行赋值
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * @return 返回 registTime
     */
    public Date getRegistTime()
    {
        return registTime;
    }

    /**
     * @param 对registTime进行赋值
     */
    public void setRegistTime(Date registTime)
    {
        this.registTime = registTime;
    }

    /**
     * @return 返回 status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    
}
