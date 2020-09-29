package codes.nttuan.controllers.common;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUtil.getSession().removeValue(req, SystemConstant.USER_MODEL);
        resp.sendRedirect("/home");
    }
}
