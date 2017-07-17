package cn.ifox.book.Servlet;

import cn.ifox.book.factory.ServiceFactory;
import cn.ifox.book.vo.Books;
import cn.ifox.book.vo.Lenbook;
import cn.ifox.book.vo.Member;
import cn.ifox.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/10.
 */
@WebServlet(name = "lenbookServlet",urlPatterns = "/pages/back/lenbook/LenbookServlet/*")
public class LenbookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/pages/error.jsp";
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insert".equals(status)) {
                path = this.insert(req);
            }else if("insertPro".equals(status)){
                path = this.insertPro(req);
            }else if("listSplit".equals(status)){
                path = this.listSplit(req);
            }else if("updateByRetdate".equals(status)){
                path = this.updateByRetdate(req);
            }

        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    private String updateByRetdate(HttpServletRequest req) {
        String msg = "";
        // 接收参数
        int leid = Integer.parseInt(req.getParameter("leid"));
        int bid = Integer.parseInt(req.getParameter("bid"));
        try {
            if(ServiceFactory.getILenbookServiceInstence().updateByRetdate(leid,bid)){
                msg = "图书已经归还!";
            }else {
                msg = "数据错误,更新失败!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("msg",msg);
        req.setAttribute("url","/pages/back/lenbook/LenbookServlet/listSplit");
        return "/pages/forward.jsp";
    }

    private String listSplit(HttpServletRequest request) {
        int currentPage = 1;
        int lineSize = 5;
        try {
            currentPage = Integer.parseInt(request.getParameter("cp"));
        }catch (Exception e){}
        try {
            lineSize = Integer.parseInt(request.getParameter("ls"));
        }catch (Exception e){}
        String keyWord = request.getParameter("kw");
        String column = request.getParameter("col");
        if(keyWord == null){
            keyWord = "";
        }
        if (column == null){
            column = "leid";
        }

        try {
            Map<String,Object> map = ServiceFactory.getILenbookServiceInstence().listBySplit(column,keyWord,currentPage,lineSize);
            request.setAttribute("allLenbooks",map.get("allLenbooks"));
            request.setAttribute("allRecorders",map.get("allLenbookCounts"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("url","/pages/back/lenbook/LenbookServlet/listSplit");
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("lineSize",lineSize);
        return "/pages/back/lenbook/lenbook_list.jsp";
    }

    private String insertPro(HttpServletRequest req) {
        try {
            Map<String,Object> map = ServiceFactory.getILenbookServiceInstence().listByMemberAndBooks();
            req.setAttribute("allMembers",map.get("allMembers"));
            req.setAttribute("allBooks",map.get("allBooks"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/lenbook/lenbook_insert.jsp";
    }

    private String insert(HttpServletRequest req) {
        String msg = "";
        String url = "";
        int bid =0;
        if(req.getParameter("bid")!=null) {
            bid = Integer.parseInt(req.getParameter("bid"));
        }
        String mid = req.getParameter("mid");
        if(ValidateUtils.validateEmpty(mid)){
            Lenbook vo = new Lenbook();
            Books books = new Books();
            books.setBid(bid);
            vo.setBooks(books);
            Member member = new Member();
            member.setMid(mid);
            vo.setMember(member);
            vo.setCredate(new Date());
            try {
                if(ServiceFactory.getILenbookServiceInstence().insert(vo)){
                    msg="数据录入成功";
                }else {
                    msg = "数据录入失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            msg = "输入字段不能为空";
        }
        req.setAttribute("msg",msg);
        req.setAttribute("url","/pages/back/lenbook/LenbookServlet/insertPro");
        return "/pages/forward.jsp";
    }
    
}
