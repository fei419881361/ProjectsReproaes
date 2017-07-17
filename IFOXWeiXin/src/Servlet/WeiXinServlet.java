package Servlet;

import Utils.ConnectionToWeiXin;
import Utils.ReceiveMessageUtils.ReceiveMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 41988 on 2017/7/16.
 */
public class WeiXinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        firstConnectToWeiXin(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ReceiveMessage.ReceiveMessage(req,resp);
    }





    private void firstConnectToWeiXin(HttpServletRequest req,HttpServletResponse resp){
        /**
         * 接收微信服务器的参数，验证结果，成功则返回echostr。
         * */
        System.out.print("------>"+"接收到微信访问");
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        PrintWriter printWriter = null;
        try {
            printWriter = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ConnectionToWeiXin.Cheksignature(signature, timestamp, nonce)){
            printWriter.print(echostr);
        }

    }
}
