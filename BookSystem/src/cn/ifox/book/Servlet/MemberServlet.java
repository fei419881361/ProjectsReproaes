package cn.ifox.book.Servlet;

import cn.ifox.book.factory.ServiceFactory;
import cn.ifox.book.vo.Member;
import cn.ifox.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/8.
 */
@WebServlet(name = "memberServlet", urlPatterns = "/pages/back/member/MemberServlet/*")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/pages/error.jsp";
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insert".equals(status)) {
                path = this.insert(req);
            } else if (status.equals("searchMember")) {
                path = this.searchMember(req);
            }
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }



    private String searchMember(HttpServletRequest req) {
        this.returnMembers(req,"searchMember");
        return "/pages/back/member/member_list.jsp";
    }

    private String insert(HttpServletRequest req) {
        String url = "";
        String msg = "";
        String mid = req.getParameter("mid");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        int sex = Integer.parseInt(req.getParameter("sex"));
        String phone = req.getParameter("phone");

        if (ValidateUtils.validateEmpty(mid) && ValidateUtils.validateEmpty(name) && ValidateUtils.validateEmpty(phone)) {
            Member vo = new Member();
            vo.setAge(age);
            vo.setPhone(phone);
            vo.setSex(sex);
            vo.setName(name);
            vo.setMid(mid);
            try {
                if (ServiceFactory.getIMemberServiceInstence().insert(vo)) {
                    url = "/pages/back/member/member_insert.jsp";
                    msg = "用户增加成功";
                } else {
                    url = "/pages/back/member/member_insert.jsp";
                    msg = "用户增加失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            url = "/pages/back/member/member_insert.jsp";
            msg = "数据不能为空";
        }
        req.setAttribute("url", url);
        req.setAttribute("msg", msg);
        return "/pages/forward.jsp";
    }
    public static void returnMembers(HttpServletRequest req,String methodname){
        Integer currentPage = 1;
        Integer lineSize = 5;
        try {
            String cp = req.getParameter("cp");
            String ls = req.getParameter("ls");
            if(cp!=null && cp!="" &&ls!=null&&ls!="") {
                currentPage = Integer.parseInt(cp);
                lineSize = Integer.parseInt(ls);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String keyword = req.getParameter("kw");
        String colum = req.getParameter("col");
        if(keyword == null){
            keyword = "";
        }if(colum == null){
            colum ="name";
        }
        try {
            Map<String,Object> map = ServiceFactory.getIMemberServiceInstence().listBySplit(colum,keyword,currentPage,lineSize);
            req.setAttribute("allMembers",map.get("allMembers"));
            req.setAttribute("allRecorders", map.get("allCounts"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("url","/pages/back/member/MemberServlet/"+methodname);
        req.setAttribute("currentPage",currentPage);
        req.setAttribute("lineSize",lineSize);
    }
}
