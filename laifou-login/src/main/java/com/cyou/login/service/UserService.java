package com.cyou.login.service;

import java.util.Map;

public interface UserService
{
    boolean isValid(String userName,String password);
    
    Map<String, Object> getModuleAndMenu(String userName);
    
}
