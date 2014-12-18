/*
 * 文 件 名:  HouseServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.laifou.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyou.laifou.house.dao.HouseDAO;
import com.cyou.laifou.house.model.House;
import com.cyou.laifou.job.service.HouseService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService
{

    @Autowired
    private HouseDAO houseDAO;
    @Override
    public void insertHouseInfo(House house)
    {
        // TODO Auto-generated method stub
        houseDAO.insert(house);
    }
    
}
