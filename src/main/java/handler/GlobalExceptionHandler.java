package handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ShaoJ
 * Date: 2020/7/4
 * Time: 15:44
 */
/*
@ControllerAdvice:控制器增强 给控制器增加异常处理功能
需要让框架知到这个包，需要增加组件扫描器
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    //定义处理异常的方法
    /*
    处理异常的方法可以和控制器方法定义一样

    形参：Exception，表示Controller中抛出的异常对象
    通过形参可以获取发生的异常信息。

    @ExceptionHandler(异常的class)：表示异常的类型，当发生此类异常交由当前方法处理
     */
    @ExceptionHandler
    public ModelAndView doOtherException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","服务器开小差蜡!");
        mv.addObject("ex",ex.getMessage());
        mv.setViewName("redirect:pages/error/myerror.jsp");
        return mv;
    }
}
