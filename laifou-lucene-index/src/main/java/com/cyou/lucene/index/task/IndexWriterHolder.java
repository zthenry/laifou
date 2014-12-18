/*
 * 文 件 名:  IndexWriterHolder.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-30
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.task;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class IndexWriterHolder
{
    private IndexWriter indexWriter;
    
    private IndexWriterHolder(){
    try
    {
        Directory directory = FSDirectory.open(new File("/home/henry/luceneindex/xian"));
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_0, analyzer);
        this.indexWriter = new IndexWriter(directory, config);
    }
    catch (IOException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
     
    }
    
    private static class IndexWriterHolderPlace{
        private final static IndexWriterHolder instance = new IndexWriterHolder();
    }
    
    public static IndexWriterHolder getInstance(){
        return IndexWriterHolderPlace.instance;
    }
    
    public synchronized IndexWriter getIndexWriter(){
        return indexWriter;
    } 
    
}
