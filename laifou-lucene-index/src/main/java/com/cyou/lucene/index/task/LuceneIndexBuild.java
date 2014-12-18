/*
 * 文 件 名:  LuceneIndexBuild.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-30
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.task;

import java.io.IOException;

import org.apache.lucene.index.IndexWriter;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LuceneIndexBuild
{
    
    public abstract void buildIndex() throws IOException;
    
    public abstract Long getId();
    
    public IndexWriter getIndexWriter(){
        return IndexWriterHolder.getInstance().getIndexWriter();
    }    
}
