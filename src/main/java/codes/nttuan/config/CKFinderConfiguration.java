package codes.nttuan.config;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.models.UserModel;
import codes.nttuan.utils.SessionUtil;
import com.ckfinder.connector.configuration.Configuration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

//ref: https://docs-old.ckeditor.com/CKFinder_2.x/Developers_Guide/Java/Configuration/Extending
public class CKFinderConfiguration extends Configuration {
    public CKFinderConfiguration(ServletConfig servletConfig) {
        super(servletConfig);
    }

    @Override
    protected Configuration createConfigurationInstance() {
        return new CKFinderConfiguration(this.servletConf);
    }

    @Override
    public String getBaseURL() {
        return SystemConstant.BASE_URL;
    }

    @Override
    public String getBaseDir() {
        return SystemConstant.BASE_DIR;
    }

    @Override
    public boolean checkAuthentication(HttpServletRequest request) {
        boolean isAdmin = false;
        UserModel userModel = (UserModel) SessionUtil.getSession().getValue(request, SystemConstant.USER_MODEL);
        if(userModel != null){
            isAdmin = userModel.getRole().getCode().equals(SystemConstant.ADMIN);
        }
        return isAdmin;
    }
}
