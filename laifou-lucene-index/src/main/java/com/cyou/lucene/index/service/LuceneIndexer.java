/*
 * 文 件 名:  LuceneIndexer.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.service;

import com.cyou.laifou.lucene.model.LuceneIndexTask;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface LuceneIndexer
{
    
    public boolean add(LuceneIndexTask luceneIndexTask);
    
    public boolean delete(LuceneIndexTask luceneIndexTask);
    
    public boolean update(LuceneIndexTask luceneIndexTask);
}
