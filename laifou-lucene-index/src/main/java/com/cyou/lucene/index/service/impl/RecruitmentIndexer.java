/*
 * 文 件 名:  RecruitmentIndexer.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.service.impl;

import org.springframework.stereotype.Service;

import com.cyou.laifou.lucene.model.LuceneIndexTask;
import com.cyou.lucene.index.service.LuceneIndexer;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("recruitmentIndexer")
public class RecruitmentIndexer implements LuceneIndexer
{

    @Override
    public boolean add(LuceneIndexTask luceneIndexTask)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(LuceneIndexTask luceneIndexTask)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(LuceneIndexTask luceneIndexTask)
    {
        // TODO Auto-generated method stub
        return false;
    }

   
    
}
