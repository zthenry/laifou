package com.cyou.lucene.search.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

import com.cyou.lucene.search.model.SearchIndexCondition;

public interface SearchService
{
    List<Document> getIdsByCondition(SearchIndexCondition condition) throws IOException, ParseException;
    
    List<Document> getIdsByHouseName(String houseName) throws IOException, ParseException;
    
    void deleteIndex(Long id);
    
    void addIndex(Long id);
    
    /**
     * 初始化所有索引，即重建
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    void initAllIndex();
    
    /**
     * 添加新的索引
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    void initNewIndex();
}
