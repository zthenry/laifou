/*
 * 文 件 名:  LoginController.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.web.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cyou.login.common.ConstantsLogin;
import com.cyou.login.common.JsonUtil;
import com.cyou.login.model.User;
import com.cyou.login.service.UserService;
import com.cyou.web.common.Constants;
import com.cyou.web.login.model.LoginCommand;

/**
 * 登录控制
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-11-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/user")
public class LoginController
{
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String loginPage(){
        return "login";
    }
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,LoginCommand loginCommand){
        boolean isValid = userService.isValid(loginCommand.getUserName(), loginCommand.getPassword());
        if (isValid)
        {
            ModelAndView mv=new ModelAndView("/index");
            
            Map<String, Object> result = userService.getModuleAndMenu(loginCommand.getUserName());
            
            request.getSession().setAttribute(ConstantsLogin.LOGIN_USER, result.get(ConstantsLogin.LOGIN_USER));
            
            mv.addObject(ConstantsLogin.MENUS,JsonUtil.toJson(result.get(ConstantsLogin.MENUS)));
            mv.addObject(ConstantsLogin.MODULES,JsonUtil.toJson(result.get(ConstantsLogin.MODULES)));
            return mv;
        }
        
        return new ModelAndView("/login","error","用户名或者密码错误");
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(
                Constants.LOGIN_USER);
        if (user != null) {
            logger.info(user.getUserName() + "退出系统");
            request.getSession().removeAttribute(Constants.LOGIN_USER);
        }
        return new ModelAndView("/logout");
    }
}
