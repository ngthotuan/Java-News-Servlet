package codes.nttuan.controllers.common;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.models.UserModel;
import codes.nttuan.service.IUserService;
import codes.nttuan.utils.FormUtil;
import codes.nttuan.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Inject
    private IUserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        SessionUtil.getSession().removeValue(req, SystemConstant.ERROR_MESSAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel model = FormUtil.toModel(UserModel.class, req);
        UserModel user = userService.findOne(model.getUsername(), model.getPassword());
        String errorMessage = null;
        if(user!= null){
            if(user.getStatus() == 0){
                errorMessage = "Account Disabled";
            }
            else {
                //save session
                SessionUtil.getSession().putValue(req, SystemConstant.USER_MODEL, user);
                String returnUrl = req.getParameter("returnUrl");
                if(returnUrl != null){
                    resp.sendRedirect(returnUrl);
                }
                else if(user.getRole().getCode().equals(SystemConstant.ADMIN)){
                    resp.sendRedirect("/admin/home");
                } else{
                    resp.sendRedirect("/home");
                }
            }
        } else{
            errorMessage = "Invalid Username Or Password";
        }
        if(errorMessage != null){
            SessionUtil.getSession().putValue(req, SystemConstant.ERROR_MESSAGE, errorMessage);
            resp.sendRedirect("/login");
        }
    }
}
