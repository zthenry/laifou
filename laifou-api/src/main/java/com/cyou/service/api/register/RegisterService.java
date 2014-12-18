/*
 * 文 件 名:  RegisterService.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.register;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RegisterService
{
    /**
     * 根据手机号码以及验证码进行注册
     * 必须进行验证码与手机号的匹配验证
     * @param phoneNumber 手机号
     * @param verificationCode 验证码
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean register(String phoneNumber,String verificationCode);
    
    
}
