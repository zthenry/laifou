/*
 * 文 件 名:  Main.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-15
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-15]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MainSearch
{
    public static void main(String[] args)
    {
        String indexPath="/home/henry/luceneindex/xian";
        String q="华远海蓝城*";
        try
        {
            search(indexPath, q);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void search(String indexPath,String q) throws IOException, ParseException{
        Directory dir = FSDirectory.open(new File(indexPath));
        IndexSearcher is = new IndexSearcher(DirectoryReader.open(dir));
        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser qp = new QueryParser("housename",  analyzer);
        Query query = qp.parse(q);
        TopDocs hits = is.search(query, 500);
        for (ScoreDoc scoreDoc : hits.scoreDocs)
        {
            Document document = is.doc(scoreDoc.doc);
            System.out.println(document.get("id")+" "+document.get("housename")+" "+document.get("kfs"));
        }
    }
}
