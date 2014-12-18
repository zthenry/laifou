/*
 * 文 件 名:  PersonConnectionDAOProvider.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-8-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.dao.provider.personcontact;

import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PersonConnectionDAOProvider {
	
	
	@SuppressWarnings("rawtypes")
    public String updateRelationTypeBatch(Map map){

	    Integer relationType = (Integer) map.get("relationType");
	    String ids = (String) map.get("ids");
		
		StringBuilder sb = new StringBuilder();  
        sb.append("update person_connection set `relation_type`="+relationType+" where id in ("+ids+")");  
       
        return sb.toString(); 
	}
	
	
}
