package interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by ShaoJ
 * Date: 2020/7/9
 * Time: 22:14
 */
//拦截器 ：拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /*
    postHandle:后处理方法
        参数：
            object handler:被拦截的控制器对象
            ModelandView mv ； 处理方法的返回值
        特点：
        1.在处理器方法之后执行
        2.可以修改处理器方法的返回值mv。可以影响最后的执行结果
        3.主要对结果进行二次修正
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /*
    afterCompletion：最后执行方法
    参数
        object handler:被拦截的控制器对象
        Exception ex：程序中发生的异常
    特点
        1.在请求处理完成后执行
        框架中规定是当你的视图处理完成后，即对视图执行了forwar。就认为请求处理完成
        2.一般是做资源回收工作，程序中创建了一些对象，在这里可以删除，把占用的内存回收
    */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}