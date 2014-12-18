/*
 * 文 件 名:  UserServiceImpl.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyou.login.common.ConstantsLogin;
import com.cyou.login.dao.UserDAO;
import com.cyou.login.model.User;
import com.cyou.login.service.UserService;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("userService")
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserDAO userDAO;
    @Override
    public boolean isValid(String userName, String password)
    {
        User user = userDAO.findByUsername(userName);
        if (user!=null && user.getPassword()!=null && user.getPassword().equals(password))
        {
            return true;
        }
        return true;
    }
    
    
    @Override
    public Map<String, Object> getModuleAndMenu(String userName)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(ConstantsLogin.MENUS, userDAO.findMenus());
        result.put(ConstantsLogin.MODULES, userDAO.findModules());
        result.put(ConstantsLogin.LOGIN_USER, userDAO.findByUsername(userName));
        return result;
    }
    
}
