/*
 * 文 件 名:  Menu.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
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
 * @author  root
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Menu
{
    private Integer id;
    
    private String name;
    
    private String url;
    
    private Integer status;
    
    private Integer moduleId;
    
    private Integer assignable;

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
     * @return 返回 name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return 返回 url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param 对url进行赋值
     */
    public void setUrl(String url)
    {
        this.url = url;
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
     * @return 返回 moduleId
     */
    public Integer getModuleId()
    {
        return moduleId;
    }

    /**
     * @param 对moduleId进行赋值
     */
    public void setModuleId(Integer moduleId)
    {
        this.moduleId = moduleId;
    }

    /**
     * @return 返回 assignable
     */
    public Integer getAssignable()
    {
        return assignable;
    }

    /**
     * @param 对assignable进行赋值
     */
    public void setAssignable(Integer assignable)
    {
        this.assignable = assignable;
    }
    
    
}
