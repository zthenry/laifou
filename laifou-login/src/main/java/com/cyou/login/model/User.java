/*
 * 文 件 名:  User.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.login.model;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class User
{
    /** id */
    protected Integer id;

    /** userName */
    protected String userName;

    /** password */
    protected String password;

    /** status */
    protected Integer status;

    protected String email;

    /**
     * @return 返回 id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return 返回 userName
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param 对userName进行赋值
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return 返回 password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param 对password进行赋值
     */
    public void setPassword(String password)
    {
        this.password = password;
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

    /**
     * @return 返回 email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param 对email进行赋值
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    
}
