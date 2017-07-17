package cn.ifox.book.Servlet;

import cn.ifox.book.factory.ServiceFactory;
import cn.ifox.book.vo.Item;
import cn.ifox.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 41988 on 2017/7/9.
 */
@WebServlet(name = "itemServlet",urlPatterns = "/pages/back/item/ItemServlet/*")
public class ItemServlet extends HttpServlet{
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
            }
            if("list".equals(status)){
                path = this.list(req);
            }
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }
    public String insert(HttpServletRequest request){
        String msg = "";
        String url = "";
        String name  = request.getParameter("name");
        String note = request.getParameter("note");
        if(ValidateUtils.validateEmpty(name)&&ValidateUtils.validateEmpty(note)){
            Item vo = new Item();
            vo.setName(name);
            vo.setNote(note);
            try{
                if (ServiceFactory.getIItemServiceInstence().insert(vo)){
                    msg = "数据增加成功";
                    url = "/pages/back/item/item_insert.jsp";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            msg = "信息不能为空";
            url = "/pages/back/item/item_insert.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String list(HttpServletRequest request){
        try {
            request.setAttribute("allItems",ServiceFactory.getIItemServiceInstence().list());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/pages/back/item/item_list.jsp";
    }
}
