/*
 * 文 件 名:  AdSpaceDAOProvider.java
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-8-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.user.analysis.dao.provider;

import java.util.Date;
import java.util.Map;

import com.cyou.util.Pagination;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RegisterPhoneDAOProvider {
	
	
	@SuppressWarnings("rawtypes")
    public String selectByPhoneDatePage(Map map){

	    String phone = (String) map.get("phone");
	    Date begin = (Date) map.get("begin");
	    Date end = (Date) map.get("end");
		
		Pagination page = (Pagination) map.get("page");
		
		StringBuilder sb = new StringBuilder();  
        sb.append("select id,phone,regist_time as registTime, status from register_phone where 1=1 ");  
       
        if (phone!=null && !phone.trim().equals("")) {
			sb.append(" and phone="+phone+" ");
		}
        if (begin!=null) {
			sb.append(" and regist_time>="+begin+" ");
		}
        if (end!=null) {
            sb.append(" and regist_time<="+end+" ");
        }
        sb.append(" LIMIT "+page.getStartIndex()+","+page.getPageSize()+" ");
        
        return sb.toString(); 
	}
	
	
}
