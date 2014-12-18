/*
 * 文 件 名:  PersonConnection.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.personcontact.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PersonConnection implements Serializable
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1331265375961567308L;

    private Long id;
    
    private Long masterPhoneId;
    
    private Long slaverPhoneId;
    
    private Date createTime;
    
    private Integer relationType;

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
     * @return 返回 masterPhoneId
     */
    public Long getMasterPhoneId()
    {
        return masterPhoneId;
    }

    /**
     * @param 对masterPhoneId进行赋值
     */
    public void setMasterPhoneId(Long masterPhoneId)
    {
        this.masterPhoneId = masterPhoneId;
    }

    /**
     * @return 返回 slaverPhoneId
     */
    public Long getSlaverPhoneId()
    {
        return slaverPhoneId;
    }

    /**
     * @param 对slaverPhoneId进行赋值
     */
    public void setSlaverPhoneId(Long slaverPhoneId)
    {
        this.slaverPhoneId = slaverPhoneId;
    }

    /**
     * @return 返回 createTime
     */
    public Date getCreateTime()
    {
        return createTime;
    }

    /**
     * @param 对createTime进行赋值
     */
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    /**
     * @return 返回 relationType
     */
    public Integer getRelationType()
    {
        return relationType;
    }

    /**
     * @param 对relationType进行赋值
     */
    public void setRelationType(Integer relationType)
    {
        this.relationType = relationType;
    }
    
    
    
}
