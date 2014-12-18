/*
 * 文 件 名:  RegisterPhoneDAO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.service.api.dao.register;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.cyou.service.api.personcontact.model.PersonConnection;
import com.cyou.service.api.register.model.RegisterPhone;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RegisterPhoneDAO
{
    public static final String ALIGNNAME ="alias_register_phone0";

    public static final String VIEW = "alias_register_phone0.`id`, alias_register_phone0.`phone`, alias_register_phone0.`regist_time` as registTime, alias_register_phone0.`status`";

    public static final String TABLENAME ="register_phone";

    @Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+"")
    public List<RegisterPhone> findALLRegister();
    
    @Select("select count(1) from "+TABLENAME+" "+ALIGNNAME+"")
    public Long countALLRegister();
    
    /**
     * 导入联系人
     * <功能详细描述>
     * @param phone
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Insert("insert ignore into "+TABLENAME+"(phone, regist_time,status) values(#{phone}, now(),#{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id", useCache = false, flushCache = true) 
    public Long insertForImport(RegisterPhone registerPhone);
    
    /**
     * 注册
     * <功能详细描述>
     * @param phone
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Insert("insert into "+TABLENAME+"(phone, regist_time,status) values(#{phone}, now(),1) ON DUPLICATE KEY UPDATE status=1")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id", useCache = false, flushCache = true)
    public void insertForRegist(RegisterPhone registerPhone);
    
    
    @Select("select "+ALIGNNAME+".id from "+TABLENAME+" "+ALIGNNAME+" where "+ALIGNNAME+".phone=#{phone}")
    public Long selectByPhone(String phone);
    
    @Select("select "+ALIGNNAME+".phone from "+TABLENAME+" "+ALIGNNAME+" where "+ALIGNNAME+".id=#{id}")
    public Long selectById(Long id);
    
    
    
}
