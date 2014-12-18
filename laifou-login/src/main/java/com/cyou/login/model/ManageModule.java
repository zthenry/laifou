/*
 * 文 件 名:  ManageModule.java
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
public class ManageModule
{
    /** 管理项id */
    protected Integer id;

    /** 管理项名称 */
    protected String name;

    /** 求请页面地址 */
    protected String url;

    /** 管理项状态：1显示 0隐藏 */
    protected Integer status;

    /** 管理项顺序 */
    protected Integer orderNum;

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
     * @return 返回 orderNum
     */
    public Integer getOrderNum()
    {
        return orderNum;
    }

    /**
     * @param 对orderNum进行赋值
     */
    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }
    
    
}
