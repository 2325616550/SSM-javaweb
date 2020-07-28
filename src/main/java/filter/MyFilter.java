package filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ShaoJ
 * Date: 2020/7/10
 * Time: 15:02
 */
public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        Boolean flag=false;
        String name = null;
        String password = null;
        //1.先从session中找用户，如果session中找到用户，说明已经登录，直接使用此用户对象
        HttpSession session = request.getSession(false);
        if(session!=null &&session.getAttribute("username")!=null)
            flag=true;
        //2.如果session中找不到，再从cookie中找用户信息(用户名和密码)，如果cookie中有，则查询数据库，再生成相应的session
        if(flag!=true) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("username".equals(c.getName())) {
                        name = c.getValue();
                    }
                    if ("password".equals(c.getName())) {
                        password = c.getValue();
                    }
                }
                ServletContext servletContext = request.getServletContext();
                WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
                UserService userService = ctx.getBean("userServiceImpl", UserService.class);
                if (name != null && password != null) {
                    if ( userService.isUser(name, password)) {
                        session = request.getSession(true);
                        session.setAttribute("username", name);
                        flag = true;
                    }
                }
            }
        }
        if(flag!=true) {
            if (uri.indexOf("login") != -1 || uri.indexOf("regist") != -1 || uri.indexOf("remind") != -1)
                flag = true;
        }
        if (flag) {
            filterChain.doFilter(request, response);
        }
        else {
            response.sendRedirect("/MyApp/remind.jsp");
        }
    }
}
