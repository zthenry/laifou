/*
 * 文 件 名:  IndexCategoryEnum.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.lucene.index.model;

/**
 * 索引创建 类别
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum IndexCategoryEnum
{
    //1:招聘  2:求职
    RECRUITMENT(1),JOBAPPLY(2);
    
    int value;
    
    public int getValue(){
        return value;
    }
    
    private IndexCategoryEnum(int value){
        this.value=value;
    }
}
