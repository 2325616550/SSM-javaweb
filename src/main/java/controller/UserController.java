package controller;


import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import utils.WebBeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;

import com.google.code.kaptcha.Producer;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
/**
 * Created by ShaoJ
 * Date: 2020/7/10
 * Time: 10:29
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private Gson gson;

    final int SEVEN_DAY = 60 * 60 * 24 * 7;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        ModelAndView mv = new ModelAndView();
        if (token != null && token.equalsIgnoreCase(code)) {
            HttpSession session1 = req.getSession(false);
            if (session1 != null && session1.getAttribute("username") != null) {
                mv.addObject("msg", "请不要重复登录哦！");
                mv.setViewName("redirect:pages/error/reptLogin.jsp");
                return mv;
            }
            boolean isTrue = userService.isUser(name, password);

            if (isTrue) {
                HttpSession session = req.getSession(true);
                session.setAttribute("username", name);
                Cookie nameCookie = new Cookie("username", name); //可以使用md5或着自己的加密算法保存
                Cookie passwordCookie = new Cookie("password", password);
                nameCookie.setPath("/");
                nameCookie.setMaxAge(SEVEN_DAY);
                passwordCookie.setPath("/");
                passwordCookie.setMaxAge(SEVEN_DAY);
                resp.addCookie(nameCookie);
                resp.addCookie(passwordCookie);
                mv.setViewName("redirect:pages/user/login_success.jsp");
            } else {
                mv.addObject("msg", "用户名或密码错误");
                mv.addObject("name", name);
                mv.setViewName("redirect:pages/user/login.jsp");
            }
        } else {
            // 把回显信息，保存到Request域中
            mv.addObject("msg", "验证码错误！");
            mv.addObject("name", name);
            mv.addObject("password", password);
            mv.setViewName("redirect:pages/user/login.jsp");
        }
        return mv;
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mv = new ModelAndView();
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //  1、获取请求的参数
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebBeanUtils.copyParamToBean(req.getParameterMap(), new User());

//        2、检查 验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (!userService.checkName(name)) {
                // 把回显信息，保存到Request域中
                mv.addObject("msg", "用户名已存在！");
                mv.addObject("name", name);
                mv.addObject("email", email);
//        跳回注册页面
                mv.setViewName("redirect:pages/user/regist.jsp");
            } else {
                //      可用
//                调用Service保存到数据库
                userService.addUser(user);
//
//        跳到注册成功页面 regist_success.jsp
                mv.setViewName("redirect:pages/user/regist_success.jsp");
            }
        } else {
            // 把回显信息，保存到Request域中
            mv.addObject("msg", "验证码错误！");
            mv.addObject("name", name);
            mv.addObject("email", email);
            mv.setViewName("redirect:pages/user/regist.jsp");
        }
        return mv;
    }

    @RequestMapping("loginimage.do")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "updateEmail", method = RequestMethod.POST)
    public void updateEmail(HttpServletRequest req, String email) {
        String username = (String) req.getSession().getAttribute("username");
        userService.changeEmail(email, username);
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public void updatePassword(HttpServletRequest req, HttpServletResponse resp, String password) {
        String username = (String) req.getSession().getAttribute("username");
        userService.changePassword(password, username);
        Cookie cookie = new Cookie("password", password);
        cookie.setMaxAge(SEVEN_DAY);
        cookie.setPath("/");
        resp.addCookie(cookie);
    }

    @RequestMapping(value = "updateName", method = RequestMethod.POST)
    public void updateName(HttpServletRequest req, HttpServletResponse resp, String name) throws RuntimeException {
        String username = (String) req.getSession().getAttribute("username");
        boolean flag = userService.checkName(name);
        if (flag) {
            String imgPath = userService.getImgPath(username);
            userService.changeName(name, username);
            req.getSession().setAttribute("username", name);
            Cookie cookie = new Cookie("username", name);
            cookie.setMaxAge(SEVEN_DAY);
            cookie.setPath("/");
            resp.addCookie(cookie);
            if (!imgPath.endsWith("default.jpg")) {
                String localPath = "C:\\File\\";
                String filename=null;
                int begin = imgPath.lastIndexOf(".");
                int end=imgPath.length();
                String suffixName = imgPath.substring(begin,end);
                File newFile = new File(localPath + name  + suffixName);
                File oldFile_jpg = new File(localPath + username + ".jpg");
                File oldFile_jpeg = new File(localPath + username + ".jpeg");
                File oldFile_png = new File(localPath + username + ".png");
                if (oldFile_jpg.isFile() && oldFile_jpg.exists()) {
                    oldFile_jpg.renameTo(newFile);
                    filename = name + ".jpg";
                }
                if (oldFile_jpeg.isFile() && oldFile_jpeg.exists()) {
                    oldFile_jpeg.renameTo(newFile);
                    filename = name + ".jpeg";
                }
                if (oldFile_png.isFile() && oldFile_png.exists()) {
                    oldFile_png.renameTo(newFile);
                    filename = name + ".png";
                }
                String sqlPath = "/static/images/" + filename;
                userService.changePhoto(sqlPath,name);
            }
        } else
            throw new RuntimeException("用户名已存在，用户名不能重复哦!");

    }

    @RequestMapping("updatePhoto")
    public ModelAndView updatePhoto(User user, HttpServletRequest req) throws Exception {
        ModelAndView mv = new ModelAndView();
        //保存数据库的路径
        String sqlPath = null;
        //定义文件保存的本地路径
        String localPath = "C:\\File\\";
        //定义 文件名
        String filename = null;
        //获得用户
        String username = (String) req.getSession().getAttribute("username");
        if (!user.getFile().isEmpty()) {
            //生成uuid作为文件名称
            String uuid = username;
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = user.getFile().getContentType();
            //获得文件后缀名
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            if(!(suffixName.endsWith("jpg")||suffixName.endsWith("jpeg")||suffixName.endsWith("png"))){
                throw new RuntimeException("头像只支持以.jpg,.png,.jpeg结尾的文件！！");
            }
            //得到 文件名
            filename = uuid + "." + suffixName;
            File delFile_jpg = new File(localPath + username + ".jpg");
            File delFile_jpeg = new File(localPath + username + ".jpeg");
            File delFile_png = new File(localPath + username + ".png");
            //删除之前图片
            if (delFile_jpg.isFile() && delFile_jpg.exists())
                delFile_jpg.delete();
            if (delFile_jpeg.isFile() && delFile_jpeg.exists())
                delFile_jpeg.delete();
            if (delFile_png.isFile() && delFile_png.exists())
                delFile_png.delete();
            //文件保存路径
            user.getFile().transferTo(new File(localPath + filename));
            //把图片的相对路径保存至数据库
            sqlPath = "/static/images/" + filename;
        }
        if (sqlPath != null) {
            userService.changePhoto(sqlPath, username);
            mv.setViewName("redirect:pages/user/update_success.jsp");
        }else{
            mv.setViewName("redirect:pages/error/myerror.jsp");
        }
        return mv;
    }
}

