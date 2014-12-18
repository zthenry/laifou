/*
 * 文 件 名:  CommonUtils.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommonUtils
{
    private static final String MOBILE_PHONE_REGEXP = "^[1][3-9]{2}[0-9]{8}$";
    
    public static boolean isMobilePhoneNum(String num)
    {
        Pattern p = Pattern.compile(MOBILE_PHONE_REGEXP);
        
        Matcher m = p.matcher(num);
        
        return m.find();
    }
}
