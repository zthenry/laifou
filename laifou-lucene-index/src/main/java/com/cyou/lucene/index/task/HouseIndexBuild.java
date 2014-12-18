/*
 * 文 件 名:  HouseIndexBuild.java
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
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import com.cyou.laifou.house.model.House;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HouseIndexBuild<S extends House> extends LuceneIndexBuild
{

    
    
    public S s;

    public HouseIndexBuild(S s,String indexpath,Analyzer analyzer){
       
        this.s=s;
        
    }
    @Override
    public void buildIndex() throws IOException
    {
        IndexWriter indexWriter = getIndexWriter();
        String cityname = s.getCityName();
        String address = s.getAddress();
        String housename = s.getHouseName();
        Long id = s.getId();
        String kfs = s.getKfsName();
        Integer price = s.getPrice();
        Date createTime = s.getCreateTime();
        try
        {
            
            Document document = new Document();
            //StoredField 不索引，不分词
            document.add(new StringField("id", id+"",Store.YES));
            document.add(new StoredField("address", address));
            document.add(new LongField("createtime", createTime.getTime(), Store.YES));
            //StringField 只索引，不分词
            document.add(new StringField("housename", housename, Store.YES));
            document.add(new StringField("cityname", cityname, Store.YES));
            document.add(new LongField("price", price, Store.YES));
            //StringField 即索引，又分词
            document.add(new TextField("kfs", kfs, Store.YES));
            indexWriter.addDocument(document);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    @Override
    public Long getId()
    {
       return s.getId();
    }

    
}
