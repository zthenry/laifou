/*
 * 文 件 名:  JobIndexerProvider.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.factory;

import com.cyou.lucene.index.service.LuceneIndexer;
import com.cyou.lucene.index.service.impl.JobApplyIndexer;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class JobApplyIndexerProvider implements IndexerProvider
{

    @Override
    public LuceneIndexer getInstance()
    {
        return new JobApplyIndexer();
    }
    
}
