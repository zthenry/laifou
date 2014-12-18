package com.cyou.laifou.houseprice.service;

import java.util.List;

import com.cyou.laifou.house.model.Condition;
import com.cyou.laifou.house.model.House;

public interface HousePriceService
{
    List<House> queryHouses(Condition condition);
    
    int count(Condition condition);
    
    /**
     * 查找还未建立索引的数据
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<House> queryIndexBuildHouse();
    
    void updateIndexState(Long id,int targetStatus);
}
