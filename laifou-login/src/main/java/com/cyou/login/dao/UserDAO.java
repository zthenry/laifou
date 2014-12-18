package com.cyou.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.cyou.login.model.ManageModule;
import com.cyou.login.model.Menu;
import com.cyou.login.model.User;

public interface UserDAO
{
    public static final String ALIGNNAME ="alias_user0";

    public static final String VIEW = "alias_user0.`id`, alias_user0.`user_name` as userName, alias_user0.`password`, alias_user0.`status`,alias_user0.`email`";

    public static final String TABLENAME ="user";

    @Select("select "+VIEW+" from "+TABLENAME+" "+ALIGNNAME+" where   "+ALIGNNAME+".user_name=#{userName}")
    public User findByUsername(String userName);
    
    @Select("select mmr.name,mmr.url,mmr.module_id AS moduleId from module_menu_rela mmr ")
    public List<Menu> findMenus();
    
    @Select("select mm.id,mm.name,mm.url,mm.status from manage_module mm")
    public List<ManageModule> findModules();
}
