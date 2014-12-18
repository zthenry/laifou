/*
 * 文 件 名:  EncryptPropertyPlaceholderConfigurer.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-12-17
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.web.common;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.cyou.util.DESUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-12-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{
    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};
    
    @Override
    protected String convertProperty(String propertyName, String propertyValue)
    {
        
        //如果在加密属性名单中发现该属性
        if (isEncryptProp(propertyName))
        {
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            System.out.println(decryptValue);
            return decryptValue;
        }else {
            return propertyValue;
        }
        
    }
    
    private boolean isEncryptProp(String propertyName)
    {
        for (String encryptName : encryptPropNames)
        {
            if (encryptName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }
}
