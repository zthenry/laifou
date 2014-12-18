/*
 * 文 件 名:  MyFilter.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-11-7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.cyou.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyou.login.model.User;
import com.cyou.web.common.Constants;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  henry
 * @version  [版本号, 2014-11-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MyFilter implements Filter
{

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //String url = request.getRequestURL().toString();
        String uri = request.getRequestURI().replaceAll("/$", "");
        
        if (!uri.equals(request.getContextPath() + "/index")) {
            User user = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/logout");
                return;
            }
        }

        filterChain.doFilter(request, response);
        return;
        
    }

    @Override
    public void init(FilterConfig arg0)
        throws ServletException
    {
        // TODO Auto-generated method stub
        
    }
    
}
