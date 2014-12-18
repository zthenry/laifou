/*
 * 文 件 名:  LuceneIndexTask.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyou.laifou.house.model.House;
import com.cyou.laifou.houseprice.service.HousePriceService;
import com.cyou.lucene.index.task.HouseIndexBuild;
import com.cyou.lucene.index.task.IndexTask;
import com.cyou.lucene.index.task.IndexWriterHolder;

/**
 * lucene 索引文件创建处理器
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class LuceneIndexTaskProcessor
{
    @Autowired
    private HousePriceService housePriceService;
    
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(20);
    @Scheduled(fixedRate=500000)
    public void exec(){
        //从数据库中将任务读取出来，然后使用多线程执行
        List<House> houselist = housePriceService.queryIndexBuildHouse();
        String indexpath = "/home/henry/luceneindex/xian";
        Analyzer analyzer = new SmartChineseAnalyzer();
        List<Future<Long>> resultList = new ArrayList<Future<Long>>();
        System.out.println("开始建索引");
        for (House house:houselist)
        {
            HouseIndexBuild<House> houseIndexBuild = new HouseIndexBuild<House>(house, indexpath, analyzer);
            Future<Long> future = EXECUTOR_SERVICE.submit(new IndexTask(houseIndexBuild));
            resultList.add(future);
            
        }
        System.out.println("索引任务提交完毕");
        System.out.println("更新数据库");
        for (Future<Long> future : resultList)
        {
            try
            {
                Long result = future.get();
                housePriceService.updateIndexState(result, 1);
                
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (ExecutionException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("更新数据库完毕");
        System.out.println("close IndexWriter");
        try
        {
            IndexWriterHolder.getInstance().getIndexWriter().close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("close IndexWriter over");
    }
}
