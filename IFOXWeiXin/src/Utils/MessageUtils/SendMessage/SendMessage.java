package Utils.MessageUtils.SendMessage;

import Utils.MessageUtils.ConstantMessage;
import Utils.MessageUtils.MessageToXML;
import domain.Message.Message;
import domain.Message.News;
import domain.Message.NewsMessage;
import domain.Message.TextMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/16.
 */
public class SendMessage {
    public static void SendTextMessage(TextMessage message, HttpServletResponse response,String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(message.getToUserName());
        textMessage.setToUserName(message.getFromUserName());
        textMessage.setMsgType(ConstantMessage.MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime()+"");
        textMessage.setContent(content);
        String sendMessage = MessageToXML.TextMessageToXML(textMessage);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(sendMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printWriter.close();
        }
    }
    public static void SendSubscribesMessage(Map<String,String>map,HttpServletResponse response){
        PrintWriter printWriter = null;
       TextMessage textMessage = new TextMessage();
        textMessage.setContent(MenuInfo());
        textMessage.setMsgType(ConstantMessage.MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime()+"");
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setToUserName(map.get("FromUserName"));
        String message = MessageToXML.TextMessageToXML(textMessage);
        try {
            printWriter = response.getWriter();
            printWriter.print(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String MenuInfo(){
        StringBuffer sb = new StringBuffer();
        sb.append("请使用以下功能对应的序号，选择相应的功能\n\n");
        sb.append("【1】搜索附近\n");
        sb.append("【2】授权登录\n");
        sb.append("【3】问卷调查\n");
        sb.append("【4】获取二维码\n");
        sb.append("【5】在线客服\n");
        sb.append("【6】与智能机器人聊天\n");
        return sb.toString();
    }
    public static void SendNewsMessage(Map<String,String> map,HttpServletResponse response){
        String message = null;
        List<News> list = new ArrayList<>();
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(map.get("FromUserName"));
        newsMessage.setFromUserName(map.get("ToUserName"));
        newsMessage.setCreateTime(new Date().getTime()+"");
        newsMessage.setArticleCount(1);
        News news = new News();
        news.setDescription("栉风沐雨终成才，学富五车方出山");
        news.setPicUrl(ConstantMessage.URLADDRESS+"/pic/weixinpic.jpg");
        news.setTitle("2017年");
        news.setUrl("http://115.29.45.115/");
        list.add(news);
        newsMessage.setArticles(list);
        newsMessage.setMsgType(ConstantMessage.MESSAGE_NEWS);
        message = MessageToXML.NewsMessageToXml(newsMessage);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printWriter.close();
        }
    }
}
