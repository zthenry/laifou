/*
 * 文 件 名:  LoginCommand.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.web.login.model;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LoginCommand
{
    private String userName;
    private String password;
    /**
     * @return 返回 userName
     */
    public String getUserName()
    {
        return userName;
    }
    
    /**
     * @return 返回 password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param 对userName进行赋值
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @param 对password进行赋值
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    
    
}
