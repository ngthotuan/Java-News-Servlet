package codes.nttuan.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    public static <T> T toModel(Class<T> tClass, HttpServletRequest request){
        T object = null;
        try{
            object = tClass.newInstance();
            BeanUtils.populate(object, request.getParameterMap());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println(e.getMessage());
        }
        return object;
    }
}
