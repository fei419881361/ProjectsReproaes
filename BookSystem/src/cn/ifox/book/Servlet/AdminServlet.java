package cn.ifox.book.Servlet;

import cn.ifox.book.factory.ServiceFactory;
import cn.ifox.book.vo.Admin;
import cn.ifox.util.MD5Code;
import cn.ifox.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 41988 on 2017/7/8.
 */
@WebServlet(name = "adminServlet" ,urlPatterns = "/pages/back/AdminServlet/*")
public class AdminServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path ="/pages/errors.jsp";
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+1);
        if(status != null){
            if("login".equals(status)){
                path = this.login(req);
            }else if("logout".equals(status)){
                path = this.logout(req);
            }
        }
        req.getRequestDispatcher(path).forward(req,resp);
    }

    private String logout(HttpServletRequest req) {
        req.getSession().removeAttribute("aid");
        req.getSession().removeAttribute("flag");
        req.getSession().removeAttribute("lastdate");
        String msg = "成功退出！";
        String url = "/pages/back/index.jsp";
        req.setAttribute("msg",msg);
        req.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String login(HttpServletRequest request) {
        String msg = ""; //表示提示信息
        String url = ""; // 表示跳转路径
        // 取得页面中传递过来数据
        String aid = request.getParameter("aid");
        String password = request.getParameter("password");
        // 判断数据是否为空
        if (ValidateUtils.validateEmpty(aid) && ValidateUtils.validateEmpty(password)) { // 表示数据存在
            Admin vo = new Admin();
            vo.setAid(aid); // 取得参数
            vo.setPassword(new MD5Code().getMD5ofStr(password + aid)); // 需要加盐处理
            try {
                if (ServiceFactory.getIAdminServiceInstence().login(vo)) {
                    request.getSession().setAttribute("aid", aid); // 保存aid
                    request.getSession().setAttribute("flag", vo.getFlag()); // 保存flag
                    request.getSession().setAttribute("lastdate", vo.getLastdate()); // 保存lastdate
                    msg = "登录成功！";
                    url = "/pages/back/index.jsp";
                } else {
                    msg = "登录失败，错误的ID或密码!";
                    url = "/login.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "数据不能为空!";
            url = "/login.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }
}
