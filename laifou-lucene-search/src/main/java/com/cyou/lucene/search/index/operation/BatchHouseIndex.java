/*
 * 文 件 名:  BatchHouseIndex.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-12-4
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.search.index.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cyou.laifou.house.model.House;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-12-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BatchHouseIndex extends IndexOperation
{

    private List<HouseIndex> houseIndexList=new ArrayList<HouseIndex>();
    
    private CountDownLatch taskNum;
    
    private boolean flag = false;
    
    private final static ExecutorService threadPoolService = Executors.newCachedThreadPool();
    
    public BatchHouseIndex(List<House> houselist,String indexPath){
        
        for (int i = 0; i < houselist.size(); i++)
        {
            HouseIndex houseIndex = new HouseIndex(houselist.get(i), indexPath);
            houseIndexList.add(houseIndex);
        }
        
        this.taskNum = new CountDownLatch(houselist.size());
        
    }
    
    
    /**
     * @return 返回 flag
     */
    public boolean isFlag()
    {
        return flag;
    }



    @Override
    public void delete()
    {
        
        for (int i = 0; i < houseIndexList.size(); i++)
        {
            houseIndexList.get(i).delete();
        }
    }

    @Override
    public void add()
    {
        for (int i = 0; i < houseIndexList.size(); i++)
        {
            houseIndexList.get(i).add();
        }
        
    }

    @Override
    public void update()
    {
        System.out.println("开始更新索引信息:"+houseIndexList.size());
        flag=false;
        for (int i = 0; i < houseIndexList.size(); i++)
        {
            final HouseIndex houseIndex= houseIndexList.get(i);
            Runnable indexTask = new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        houseIndex.update();
                    }
                    catch (Exception e)
                    {
                        // TODO: handle exception
                    }finally{
                        taskNum.countDown();
                    }
                    
                    
                }
            };
            threadPoolService.submit(indexTask);
        }
        
        try
        {
            taskNum.await();
            System.out.println("所有任务执行完成");
            flag=true;
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
