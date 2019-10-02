package com.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	private List<String> excludeUrls;// 不需要拦截的资源
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}


	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		excludeUrls.add(0, "/user/login");
		excludeUrls.add(1, "/user/register");
		excludeUrls.add(2, "/admin/login");
	    String contextPath=request.getContextPath();
	    String  url=request.getServletPath().toString();
	    HttpSession session =  request.getSession();
	    //System.out.println("url is: " + url);
	      if(excludeUrls.contains(url)){ 
	    	  //System.out.println("yes");
	    	  return true ;
	      }

	      if(session.getAttribute("user")!=null || session.getAttribute("admin")!= null) {
	          // 登录成功不拦截
	          return true;
	        }else {
	          // 拦截后进入登录页面
	          response.sendRedirect(request.getContextPath()+"/login.jsp");
	          return false;
	        }
	    
	}

}
