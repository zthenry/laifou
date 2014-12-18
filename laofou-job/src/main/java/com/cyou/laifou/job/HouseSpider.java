/*
 * 文 件 名:  HouseSpider.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.laifou.job;

import java.util.Date;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyou.laifou.house.model.House;
import com.cyou.laifou.job.service.HouseService;
import com.cyou.util.HttpUtil;
import com.cyou.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 房价信息爬虫
 * 从搜房网爬信息
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("houseSpider")
public class HouseSpider
{
    @Autowired
    private HouseService houseService;
    
    private final Logger logger = LoggerFactory.getLogger(HouseSpider.class);
    
    @Scheduled(fixedRate = 500000)
    public void getHousePrice()
    {
        
        logger.error("getNewHousePrice......");
        String newhourcePriceUrl =
            "http://map.fang.com/house/search.php?ajaxsendtype=jsonp&city=xian&cname=%E8%A5%BF%E5%AE%89&citycode=029&output=json&purposes=%E4%BD%8F%E5%AE%85&num=20&page=";
        int maxPage = 1;
        try
        {
            //获取最大页数
            String data = HttpUtil.syncGet(newhourcePriceUrl + "1", null, null, null);
            maxPage = JacksonUtil.getJsonMapper().readTree(data.substring(1, data.length() - 1)).get("maxpage").asInt();
            System.out.println(maxPage);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        for (int i = 1; i <= maxPage; i++)
        {
            System.out.println("第" + i + "页 begin......");
            
            try
            {
                
                String data = HttpUtil.syncGet(newhourcePriceUrl + i, null, null, null);
                Iterator<JsonNode> iter =
                    JacksonUtil.getJsonMapper().readTree(data.substring(1, data.length() - 1)).get("hits").elements();
                while (iter.hasNext())
                {
                    JsonNode node = iter.next();
                    String title = node.get("title").asText();
                    String address = node.get("address").asText();
                    String developer = node.get("developer").asText();
                    int price = node.get("price_num").asInt();
                    String city = node.get("city").asText();
                    System.out.println(title + " " + address + " " + developer + " " + price + " " + city);
                    House house = new House();
                    house.setPrice(price);
                    house.setCityName(city);
                    house.setAddress(address.trim());
                    house.setCreateTime(new Date());
                    house.setHouseName(title);
                    if (developer==null || developer.trim().equals(""))
                    {
                        developer="暂无";
                    }
                    house.setKfsName(developer);
                    
                    houseService.insertHouseInfo(house);
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
            System.out.println("第" + i + "页 end***********");
        }
    }
    
}
