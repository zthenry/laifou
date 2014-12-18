/*
 * 文 件 名:  HousePriceServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.laifou.houseprice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyou.laifou.house.dao.HouseDAO;
import com.cyou.laifou.house.model.Condition;
import com.cyou.laifou.house.model.House;
import com.cyou.laifou.houseprice.service.HousePriceService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("housePriceService")
public class HousePriceServiceImpl implements HousePriceService
{

    @Autowired
    private HouseDAO houseDAO;
    @Override
    public List<House> queryHouses(Condition condition)
    {
        return houseDAO.findByCondition(condition);
    }
    @Override
    public int count(Condition condition)
    {
        return houseDAO.countByCondition(condition);
    }
    @Override
    public List<House> queryIndexBuildHouse()
    {
        return houseDAO.findHousesByIndexBuild(0);
    }
    @Override
    public void updateIndexState(Long id, int targetStatus)
    {
        houseDAO.updateIndexStatus(id, targetStatus);
        
    }
    
}
