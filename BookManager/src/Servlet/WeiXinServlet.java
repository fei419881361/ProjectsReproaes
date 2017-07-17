package Servlet;

import DAO.BookDAOProxy;
import Utils.ConnectionToWeiXin;
import Utils.MessageUtils;
import Utils.WeixinUtils;
import domain.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 41988 on 2017/5/20.
 * void firstConnectToWeiXin(req,resp):第一次接入微信后台使用。
 */
public class WeiXinServlet extends HttpServlet{
    public static String ManagerID = "oPc31vlYsyXGUfgNTRP4n05_1VwA";
    String FromUserName;
    String ToUserName;
    String CreateTime;
    String MsgType;
    String Content;
    String MsgId;
    Map<String, String> map;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        firstConnectToWeiXin(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        log(req.getContentType());

        map = MessageUtils.xmlTomap(req);
        FromUserName = map.get("FromUserName");
        System.out.println("first fromusername ="+FromUserName); //打印发送信息的用户
        ToUserName = map.get("ToUserName");
        CreateTime = map.get("CreateTime");
        MsgType = map.get("MsgType");
        Content = map.get("Content");
        MsgId = map.get("MsgId");
        System.out.println(MsgType);//打印读取到的信息类型
        GetMessage(req,resp,MsgType);


    }

    /**
     * 处理用户事件
     * */
    private void doEventMessage(PrintWriter out) {
        //Event的type
        String type = map.get("Event");
        switch (type){
            case MessageUtils.MESSAGE_EVENT_CLICK_scancode_waitmsg://扫描事件
                ScancodeMethod(out);
                break;
            case MessageUtils.MESSAGE_EVENT_CLICK://点击返回信息按钮事件
                ClickMethod(out);
                break;
        }
    }
    /**
     * 处理扫码事件类型
     * */
    private void ScancodeMethod(PrintWriter out) {
        String key = map.get("EventKey");
        BookDAOProxy proxy = new BookDAOProxy();
        if ("btn_2".equals(key)) {
            //借书的逻辑
            System.out.println("click---");
            String result = "--";
            result = map.get("ScanResult"); //获取扫描二维码的结果
            System.out.println(result);//打印结果
//            String condition = "WHERE book_name = 'xxx';";
//            condition = condition.replace("xxx", result);
            Book book = proxy.getBookbyName(result);
            //如果该书的状态是可以借阅的，那么获取用户的NickName记录进数据库。
            //否则返回提示信息，该书目前被谁借走了。（注意！后续修改：根据FromUserName,
            // 返回该用户更具体的信息）。
            if (book.getIsing()==0) {
                String From = WeixinUtils.getUserNickName(map.get("FromUserName"));//获取网名
                proxy.UpDateBookInfo(From, result);//result 为扫描结果，书籍名字
                String mString = MessageUtils.Function(FromUserName, ToUserName, "恭喜你成功借阅到了：" + result);
                out.print(mString);
            } else {
                //当前书不可借阅
                String now_user = book.getNow_user();   //当前书被谁借走
                String mString = MessageUtils.Function(FromUserName, ToUserName, "Sorry~该书被"+now_user+"借走了");
                out.print(mString);
            }
        }
        //还书的逻辑
        else if("btn_3".equals(key)){
            String result = map.get("ScanResult");
            String nickname = WeixinUtils.getUserNickName(map.get("FromUserName"));
            System.out.println("还书-"+result);//打印书名
            Book book = proxy.getBookbyName(result);
            System.out.println("还书名字"+book.getBook_name());
            //-----姓名比对-------
            if(proxy.ReturnBook(book,nickname)){
                String mString = MessageUtils.Function(FromUserName, ToUserName, "你已经成功的归还了："+result+"请放到实验室固定位置哦~，请让管理员确认归还。");
                out.print(mString);
            }else {
                String mString = MessageUtils.Function(FromUserName, ToUserName, "还书失败-。-，请与管理员联系");
                out.print(mString);
            }
        }
        else if("btn_4".equals(key)){
            String user = map.get("FromUserName");
            if(ManagerID.equals(user)){
                String result = map.get("ScanResult"); //得到扫描书籍名字
                Book book = proxy.getBookbyName(result);
                //确认书籍是 确认归还状态
                if(book.getIsing()==WeixinUtils.NOTRETURN){
                    proxy.ManagerConfirm(book);
                    String mString = MessageUtils.Function(FromUserName, ToUserName, "管理员确认完成");
                    out.print(mString);
                }
                else {
                    String mString = MessageUtils.Function(FromUserName, ToUserName, "管理员操作失败");
                    out.print(mString);
                }

            }else{
                String mString = MessageUtils.Function(FromUserName, ToUserName, "无管理员权限");
                out.print(mString);
            }
        }
    }

    /**
     * 处理点击事件类型
     * */
    private void ClickMethod(PrintWriter out){
        String key = map.get("EventKey");
        BookDAOProxy proxy = new BookDAOProxy();
        System.out.println("ClickMethod");
        //查看所有图书
        if("btn_1".equals(key)){
            //2017-02-27 17:12:23
            String mString="";
            List<Book> books = new ArrayList<Book>();
            books = proxy.getAllBook();
            for(int i=0;i<books.size();i++){
                Book book = new Book();
                book = books.get(i);
                mString = mString+book.getId()+" "+book.getBook_name()+" "+book.getIsing()+" "+book.getNow_user()+" "+book.getRecent_user()+"\n";
            }
            String mString2 = MessageUtils.Function(FromUserName, ToUserName,"\n"+mString);
            out.print(mString2);
        }


    }


    /**
     * 处理用户发送的文本消息
     * */
    private void doTexrMessage(PrintWriter out) {
        String content = "接收到了你的消息";
        String sendMsg = MessageUtils.Function(FromUserName,ToUserName,content);

        out.print(sendMsg);
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

    private void GetMessage(HttpServletRequest req,HttpServletResponse resp,String MsgType){
        try {
            PrintWriter out = resp.getWriter();
            switch (MsgType){
                case MessageUtils.MESSAGE_TEXT:
                    doTexrMessage(out);
                    break;
                case MessageUtils.MESSAGE_EVENT:
                    doEventMessage(out);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
