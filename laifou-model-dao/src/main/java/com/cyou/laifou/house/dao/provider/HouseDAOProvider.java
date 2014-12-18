/*
 * 文 件 名:  HouseDAOProvider.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.laifou.house.dao.provider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cyou.laifou.house.dao.HouseDAO;
import com.cyou.laifou.house.model.Condition;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HouseDAOProvider
{
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
    public String selectByCondition(Map map){
        Condition condition = (Condition)map.get("condition");
        
        StringBuilder sb = new StringBuilder();
        sb.append("select "+HouseDAO.VIEW+" from "+HouseDAO.TABLENAME+" "+HouseDAO.ALIGNNAME+" where 1=1 ");
        Date beginDate = condition.getBeginDate();
        Date endDate = condition.getEndDate();
        
        if (beginDate!=null)
        {
            String beginDateString = sdf.format(beginDate);
            sb.append(" and TO_DAYS("+HouseDAO.ALIGNNAME+".create_time)>=TO_DAYS('"+beginDateString+"') ");
        }
        
        if (endDate!=null)
        {
            String endDateString = sdf.format(endDate);
            sb.append(" and TO_DAYS("+HouseDAO.ALIGNNAME+".create_time)<=TO_DAYS('"+endDateString+"') ");
        }
        List<Long> ids = condition.getIds();
        String idQuery ="";
        if (ids!=null && ids.size()>0)
        {
            for (Long id : ids)
            {
                idQuery = idQuery+","+id;
            }
            idQuery=idQuery.substring(1);
            sb.append(" and id in ("+idQuery+")");
        }
        int page = condition.getCurrentPage();
        sb.append(" limit "+(page-1)*10+",10");
        return sb.toString();
    }
    
    public String countByCondition(Map map){
        Condition condition = (Condition)map.get("condition");
        Date beginDate = condition.getBeginDate();
        Date endDate = condition.getEndDate();
        condition.getCityName();
        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) from "+HouseDAO.TABLENAME+" "+HouseDAO.ALIGNNAME+" where 1=1 ");
        if (beginDate!=null)
        {
            String beginDateString = sdf.format(beginDate);
            sb.append(" and TO_DAYS("+HouseDAO.ALIGNNAME+".create_time)>=TO_DAYS('"+beginDateString+"') ");
        }
        
        if (endDate!=null)
        {
            String endDateString = sdf.format(endDate);
            sb.append(" and TO_DAYS("+HouseDAO.ALIGNNAME+".create_time)<=TO_DAYS('"+endDateString+"') ");
        }
        return sb.toString();
    }
}
