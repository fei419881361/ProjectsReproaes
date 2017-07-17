package cn.ifox.book.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 41988 on 2017/7/15.
 */
@WebServlet(name = "visitorServlet", urlPatterns = "/VisitorServlet/*")
public class VisitorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        String path = "/pages/error.jsp";
        if(status.equals("showMembers")){
            path = this.showMembers(req);
        }else if(status.equals("showBooks")){
            path = this.showBooks(req);
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    private String showBooks(HttpServletRequest req) {
        BooksServlet.returnBooks(req,"showBooks");
        return "/pages2/showBooks.jsp";
    }

    private String showMembers(HttpServletRequest req) {
       MemberServlet.returnMembers(req,"showMembers");
        return "/pages2/showMembers.jsp";
    }
}
