/*
 * 文 件 名:  RegisterAnalysisController.java
 * 描    述:  <描述>
 * 修 改 人:  henry
 * 修改时间:  2014-11-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.web.register.analysis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cyou.user.analysis.model.RegisterPhone;
import com.cyou.user.analysis.service.RegistrationService;
import com.cyou.util.Pagination;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/register")
public class RegisterAnalysisController
{
    private final Logger logger = LoggerFactory.getLogger(RegisterAnalysisController.class);
    
    @Autowired
    private RegistrationService registrationService;
    
    
    @RequestMapping(value="/listView",method=RequestMethod.GET)
    public ModelAndView registerListView(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/registerAnalysis/registerListView");
        Long totalCount = registrationService.countAll();
        Pagination page = new Pagination(1, totalCount.intValue(), 5);
        List<RegisterPhone> registerPhones = registrationService.queryRegisterPhoneByPage(null, null, null, page);
        mv.addObject("page", page);
        mv.addObject("curPage", 1);
        mv.addObject("registerPhones", registerPhones);
        return mv;
    }
    
}
