/*
 * 文 件 名:  Condition.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.laifou.house.model;

import java.util.Date;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Condition
{
    private int currentPage;
    private int perPageSize;
    
    private String cityName;
    private long houseId;
    private String houseName;
    private String kfsName;
    private Date beginDate;
    private Date endDate;
    private Integer priceRangBegin;
    private Integer priceRangEnd;
    
    private List<Long> ids;
    
    
    
    /**
     * @return 返回 houseId
     */
    public long getHouseId()
    {
        return houseId;
    }
    /**
     * @param 对houseId进行赋值
     */
    public void setHouseId(long houseId)
    {
        this.houseId = houseId;
        
    }
    /**
     * @return 返回 ids
     */
    public List<Long> getIds()
    {
        return ids;
    }
    /**
     * @param 对ids进行赋值
     */
    public void setIds(List<Long> ids)
    {
        this.ids = ids;
    }
    public Condition(){
        this.currentPage=1;
    }
    /**
     * @return 返回 currentPage
     */
    public int getCurrentPage()
    {
        return currentPage;
    }
    /**
     * @param 对currentPage进行赋值
     */
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
   
    /**
     * @return 返回 cityName
     */
    public String getCityName()
    {
        return cityName;
    }
    /**
     * @param 对cityName进行赋值
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    /**
     * @return 返回 perPageSize
     */
    public int getPerPageSize()
    {
        return perPageSize;
    }
    /**
     * @param 对perPageSize进行赋值
     */
    public void setPerPageSize(int perPageSize)
    {
        this.perPageSize = perPageSize;
    }
    /**
     * @return 返回 houseName
     */
    public String getHouseName()
    {
        return houseName;
    }
    /**
     * @param 对houseName进行赋值
     */
    public void setHouseName(String houseName)
    {
        this.houseName = houseName;
    }
    /**
     * @return 返回 kfsName
     */
    public String getKfsName()
    {
        return kfsName;
    }
    /**
     * @param 对kfsName进行赋值
     */
    public void setKfsName(String kfsName)
    {
        this.kfsName = kfsName;
    }
    /**
     * @return 返回 beginDate
     */
    public Date getBeginDate()
    {
        return beginDate;
    }
    /**
     * @param 对beginDate进行赋值
     */
    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }
    /**
     * @return 返回 endDate
     */
    public Date getEndDate()
    {
        return endDate;
    }
    /**
     * @param 对endDate进行赋值
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    /**
     * @return 返回 priceRangBegin
     */
    public Integer getPriceRangBegin()
    {
        return priceRangBegin;
    }
    /**
     * @param 对priceRangBegin进行赋值
     */
    public void setPriceRangBegin(Integer priceRangBegin)
    {
        this.priceRangBegin = priceRangBegin;
    }
    /**
     * @return 返回 priceRangEnd
     */
    public Integer getPriceRangEnd()
    {
        return priceRangEnd;
    }
    /**
     * @param 对priceRangEnd进行赋值
     */
    public void setPriceRangEnd(Integer priceRangEnd)
    {
        this.priceRangEnd = priceRangEnd;
    }
   
    
    
}
