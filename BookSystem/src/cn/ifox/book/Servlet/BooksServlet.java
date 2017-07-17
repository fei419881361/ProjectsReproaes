package cn.ifox.book.Servlet;

import cn.ifox.book.factory.ServiceFactory;
import cn.ifox.book.vo.Admin;
import cn.ifox.book.vo.Books;
import cn.ifox.book.vo.Item;
import cn.ifox.util.Validate.ValidateUtils;
import com.sun.deploy.net.HttpRequest;

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
@WebServlet(name = "booksServlet", urlPatterns = "/pages/back/books/BooksServlet/*")
public class BooksServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/pages/error.jsp";
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insertPro".equals(status)) {
                path = this.insertPro(req);
            } else if ("insert".equals(status)) {
                path = this.inset(req, resp);
            } else if ("listSplit".equals(status)) {
                path = this.listSplit(req, resp);
            }

        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    private String listSplit(HttpServletRequest req, HttpServletResponse resp) {
        returnBooks(req,"listSplit");
        return "/pages/back/books/books_list.jsp";
    }

    private String inset(HttpServletRequest req, HttpServletResponse resp) {
        String msg = "";
        String url = "";
        //取得页面中的数据
        String name = req.getParameter("name");
        String aid = req.getParameter("aid"); // req.getSession();中保存有管理员的名字
        String iid = req.getParameter("iid");
        String note = req.getParameter("note");
        if (ValidateUtils.validateEmpty(name) && ValidateUtils.validateEmpty(aid) &&
                ValidateUtils.validateEmpty(note)) {
            Books vo = new Books();
            vo.setName(name);
            vo.setNote(note);
            Admin admin = new Admin();
            admin.setAid(aid);
            vo.setAdmin(admin);
            Item item = new Item();
            item.setIid(Integer.parseInt(iid));
            vo.setItem(item);
            vo.setCredate(new Date());
            vo.setStatus(1); //1 为在架
            vo.setNote(note);
            try {
                if (ServiceFactory.getIBookServiceInstence().insert(vo)) {
                    msg = "数据增加成功";
                    url = "/pages/back/books/BooksServlet/insertPro";
                } else {
                    msg = "输入信息有误";
                    url = "/pages/back/books/BooksServlet/insertPro";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "输入不能为空";
            url = "/pages/back/books/BooksServlet/insertPro";
        }
        req.setAttribute("msg", msg);
        req.setAttribute("url", url);

        return "/pages/forward.jsp";
    }

    public String insertPro(HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = ServiceFactory.getIBookServiceInstence().findByAdminAndItem();
            request.setAttribute("allAdmins", map.get("allAdmins"));
            request.setAttribute("allItems", map.get("allItems"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/books/books_insert.jsp";
    }
    public static void returnBooks(HttpServletRequest req,String method){
        Integer currentPage = 1;
        Integer lineSize = 5;
        try {
            String cp = req.getParameter("cp");
            String ls = req.getParameter("ls");
            if (cp != null && cp != "" && ls != null && ls != "") {
                currentPage = Integer.parseInt(cp);
                lineSize = Integer.parseInt(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String keyword = req.getParameter("kw");
        String colum = req.getParameter("col");
        if (keyword == null) {
            keyword = "";
        }
        if (colum == null) {
            colum = "name";
        }
        try {
            Map<String, Object> map = ServiceFactory.getIBookServiceInstence().listBySplit(colum, keyword, currentPage, lineSize);
            req.setAttribute("allBooks", map.get("allBooks"));
            req.setAttribute("allRecorders", map.get("allCounts"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("url", "/pages/back/books/BooksServlet/"+method);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("lineSize", lineSize);
    }

}
