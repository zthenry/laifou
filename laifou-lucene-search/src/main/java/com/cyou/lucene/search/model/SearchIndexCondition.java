/*
 * 文 件 名:  SearchIndexCondition.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-30
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.search.model;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SearchIndexCondition
{
    private String kfs;
    
    private String housename;
    
    private long minPrice;
    
    private long maxPrice;
    
    private String address;

    /**
     * @return 返回 kfs
     */
    public String getKfs()
    {
        return kfs;
    }

    /**
     * @param 对kfs进行赋值
     */
    public void setKfs(String kfs)
    {
        this.kfs = kfs;
    }

    /**
     * @return 返回 housename
     */
    public String getHousename()
    {
        return housename;
    }

    /**
     * @param 对housename进行赋值
     */
    public void setHousename(String housename)
    {
        this.housename = housename;
    }


    /**
     * @return 返回 minPrice
     */
    public long getMinPrice()
    {
        return minPrice;
    }

    /**
     * @param 对minPrice进行赋值
     */
    public void setMinPrice(long minPrice)
    {
        this.minPrice = minPrice;
    }

    /**
     * @return 返回 maxPrice
     */
    public long getMaxPrice()
    {
        return maxPrice;
    }

    /**
     * @param 对maxPrice进行赋值
     */
    public void setMaxPrice(long maxPrice)
    {
        this.maxPrice = maxPrice;
    }

    /**
     * @return 返回 address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param 对address进行赋值
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    
}
