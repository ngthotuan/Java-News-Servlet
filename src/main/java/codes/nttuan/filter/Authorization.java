package codes.nttuan.filter;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.models.UserModel;
import codes.nttuan.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class Authorization implements Filter {
    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if(url.startsWith("/admin")){
            UserModel userModel = (UserModel) SessionUtil.getSession().getValue(request, SystemConstant.USER_MODEL);
            if(userModel == null){
                response.sendRedirect("/login?returnUrl="+url);
            }
            else if(userModel.getRole().getCode().equals(SystemConstant.ADMIN)){
                filterChain.doFilter(servletRequest, servletResponse);

            } else if (userModel.getRole().getCode().equals(SystemConstant.USER)){
                response.sendRedirect("/forbidden");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
