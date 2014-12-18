/*
 * 文 件 名:  HouseIndex.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-12-3
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.search.index.operation;

import java.io.IOException;
import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import com.cyou.laifou.house.model.House;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-12-3]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HouseIndex extends IndexOperation
{
    private House house;
    
    private IndexWriter indexWriter;
    
    private boolean flag = false;
    /**
     * @param 对house进行赋值
     */
    public HouseIndex(House house,String indexPath)
    {
        this.house = house;
        try
        {
            this.indexWriter=IndexWriterHolder.getInstance().getIndexWriter(indexPath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete()
    {
        try
        {
            indexWriter.deleteDocuments(new Term("id", house.getId().intValue()+""));
            System.out.println("删除记录： "+house.getId()+" "+ indexWriter.hasDeletions());
            this.flag = indexWriter.hasDeletions();
            indexWriter.commit();
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

    @Override
    public void add()
    {
        delete();
        
        String cityname = house.getCityName();
        String address = house.getAddress();
        String housename = house.getHouseName();
        Long id = house.getId();
        String kfs = house.getKfsName();
        Integer price = house.getPrice();
        Date createTime = house.getCreateTime();
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
            indexWriter.commit();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

    @Override
    public void update()
    {
        delete();
        add();
    }

    /**
     * @return 返回 flag
     */
    public boolean isFlag()
    {
        return flag;
    }
    
}
