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
package com.cyou.user.analysis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.cyou.user.analysis.dao.provider.RegisterPhoneDAOProvider;
import com.cyou.user.analysis.model.RegisterPhone;
import com.cyou.util.Pagination;

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
    
    @SelectProvider(type=RegisterPhoneDAOProvider.class, method="selectByPhoneDatePage")
    public List<RegisterPhone> findByPage(@Param("phone") String phone, @Param("begin") Date begin,@Param("end") Date end,@Param("page") Pagination page);
    
}
