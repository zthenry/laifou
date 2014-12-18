/*
 * 文 件 名:  SearchServiceImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-30
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.search.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cyou.laifou.house.dao.HouseDAO;
import com.cyou.laifou.house.model.House;
import com.cyou.lucene.search.index.operation.BatchHouseIndex;
import com.cyou.lucene.search.index.operation.HouseIndex;
import com.cyou.lucene.search.model.SearchIndexCondition;
import com.cyou.lucene.search.service.SearchService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService
{

    @Value("${indexpath}")
    private String indexpath; 
    
    @Autowired
    private HouseDAO houseDAO;
    
    @Override
    public List<Document> getIdsByCondition(SearchIndexCondition condition) throws IOException, ParseException
    {
        List<Document> docs = new ArrayList<Document>();
       
        //实现逻辑检索需要使用BooleanQuery，具体可以查询相关的逻辑关系表达式Occur
        BooleanQuery booleanQuery = new BooleanQuery();
        Directory dir = FSDirectory.open(new File(indexpath));
        IndexSearcher is = new IndexSearcher(DirectoryReader.open(dir));
        Analyzer analyzer = new SmartChineseAnalyzer();
        
        String housename=condition.getHousename();
        long minPrice = condition.getMinPrice();
        long maxPrice = condition.getMaxPrice();
        if (!StringUtils.isBlank(housename))
        {
            //使用MultiFieldQueryParser实现多域检索
            QueryParser qp = new MultiFieldQueryParser(new String[]{"housename","kfs"}, analyzer);
            //QueryParser qp = new QueryParser("housename",  analyzer);
            Query query = qp.parse(housename+"*");
            booleanQuery.add(query, Occur.MUST);
           
        }
        if (minPrice!=0 || maxPrice!=0)
        {
            if (minPrice==0)
            {
                minPrice=Long.MIN_VALUE;
            }
            if (maxPrice==0)
            {
                maxPrice=Long.MAX_VALUE;
                
            }
            //使用NumericRangeQuery实现价格区间范围的检索，因为价格的索引使用的是LongField，所以使用的是NumericRangeQuery<Long>
            NumericRangeQuery<Long> priceQuery = NumericRangeQuery.newLongRange("price", minPrice, maxPrice, true, true);
           
            booleanQuery.add(priceQuery, Occur.MUST);
        }
        
        
        //使用Sort内实现检索结果排序
        //false为升序 true为降序
        SortField sortFiledCreateTime = new SortField("createtime", SortField.Type.LONG,false);
        SortField sortFiledPrice = new SortField("price", SortField.Type.LONG,true);
        Sort sort = new Sort();
        sort.setSort(new SortField[]{sortFiledPrice,sortFiledCreateTime});
        TopDocs hits = is.search(booleanQuery, 100, sort);
        //TopDocs hits = is.search(query, 500);
        for (ScoreDoc scoreDoc : hits.scoreDocs)
        {
            Document document = is.doc(scoreDoc.doc);
            System.out.println(document.get("id")+" "+document.get("housename")+" "+document.get("kfs"));
            docs.add(document);
        }
        
        return docs;
    }
    @Override
    public List<Document> getIdsByHouseName(String houseName)
        throws IOException, ParseException
    {
        List<Document> docs = new ArrayList<Document>();
        
        if (houseName==null || houseName.trim().equals(""))
        {
            return docs;
        }
        Directory dir = FSDirectory.open(new File(indexpath));
        IndexSearcher is = new IndexSearcher(DirectoryReader.open(dir));
        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser qp = new MultiFieldQueryParser(new String[]{"housename"}, analyzer);
        //QueryParser qp = new QueryParser("housename",  analyzer);
        Query query = qp.parse(houseName+"*");
        TopDocs hits = is.search(query, 100, new Sort(new SortField("createtime", SortField.Type.LONG)));
        //TopDocs hits = is.search(query, 500);
        for (ScoreDoc scoreDoc : hits.scoreDocs)
        {
            Document document = is.doc(scoreDoc.doc);
            System.out.println(document.get("id")+" "+document.get("housename")+" "+document.get("kfs"));
            docs.add(document);
        }
        return docs;
    }
    @Override
    public void deleteIndex(Long id)
    {
        //删除文档ById
        House house = new House();
        house.setId(id);
        HouseIndex houseIndex = new HouseIndex(house, indexpath);
        houseIndex.delete();
        boolean isDelete = houseIndex.isFlag();
        if (isDelete)
        {
            houseDAO.updateIndexStatus(id, 0);
        }
    }
    @Override
    public void addIndex(Long id)
    {
        deleteIndex(id);
        
        House house= houseDAO.findById(id);
        
        HouseIndex houseIndex=new HouseIndex(house, indexpath);
        houseIndex.add();
        houseDAO.updateIndexStatus(id, 1);
    }
    @Override
    public void initAllIndex()
    {
        List<House> houselist = houseDAO.findAll();
        BatchHouseIndex bhi = new BatchHouseIndex(houselist, indexpath);
        bhi.update();
        houseDAO.changeIndexStatus(1);
        
    }
    @Override
    public void initNewIndex()
    {
        List<House> houselist = houseDAO.findHousesByIndexBuild(0);
        BatchHouseIndex bhi = new BatchHouseIndex(houselist, indexpath);
        bhi.update();
        houseDAO.changeIndexStatus(1);
    }
    
}
