/*
 * 文 件 名:  IndexTask.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.task;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * 具体的index 更新 的线程任务
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class IndexTask implements Callable<Long>
{
    private LuceneIndexBuild indexBuild;
    
    public IndexTask(LuceneIndexBuild indexBuild)
    {
        this.indexBuild=indexBuild;
        
    }
    
    @Override
    public Long call()
        throws Exception
    {
        long start = System.currentTimeMillis();
        try
        {
            indexBuild.buildIndex();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("任务执行时间:"+(end-start));
        return indexBuild.getId();
    }
   
   
    
}
