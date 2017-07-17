package Utils;

import domain.Button.Button;
import domain.Button.Button_Click;
import domain.Menu;
import domain.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

/**
 * Created by 41988 on 2017/5/20.
 *
 */
public class MessageUtils {
    /**
     * 各种消息类型
     * 各种事件类型：
     * subscribe：关注
     * unsubscribe:取消关注
     */
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_EVENT_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_EVENT_CLICK = "CLICK";
    public static final String MESSAGE_EVENT_VIEW = "VIEW";
    public static final String MESSAGE_EVENT_CLICK_scancode_waitmsg= "scancode_waitmsg";

    /**
     * 将xml转换为map
     * */
    public static Map<String, String> xmlTomap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream inputStream;
        try {
            inputStream = request.getInputStream();
            Document doc = reader.read(inputStream);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            getEliments(list, map);
            inputStream.close();
            return map;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }
    /**
     * 将文本消息转换为XML
     * */
    public static String textmessageToxml(TextMessage textmessage){
        XStream xStream = new XStream();
        xStream.alias("xml", textmessage.getClass());
        return xStream.toXML(textmessage);
    }
    /**
     * 消息拼装
     * */
    private static TextMessage Assemble(String FromUserName,String ToUserName,String Content,String type){
        TextMessage mm = new TextMessage();
        mm.setFromUserName(ToUserName);
        mm.setToUserName(FromUserName);
        mm.setMsgType(type);
        mm.setCreateTime(Long.toString(new Date().getTime()));
        mm.setContent(Content);
        return mm;
    }
    /**
     * 加工消息为最后发送时需要的XML格式
     * */
    public static String Function(String FromUserName,String ToUserName,String Content){
        TextMessage mm = Assemble(FromUserName, ToUserName, Content, MessageUtils.MESSAGE_TEXT);
        String mString = null;
        mString = MessageUtils.textmessageToxml(mm);
        return mString;
    }
    private static void getEliments(List<Element> sonEliment, Map<String, String> map) {
        for (Element element : sonEliment) {
            if (element.elements().size() != 0) {
                getEliments(element.elements(), map);
            } else {
                map.put(element.getName(),element.getText());
            }
        }
    }
    /**
     * 初始化菜单
     * */
    public static Menu initMenu(){
        Menu menu = new Menu();

//        Button_Click button_Click = new Button_Click();
//        button_Click.setName("查看所有图书");
//        button_Click.setKey("btn_1");
//        button_Click.setType("click");
//
//        Button_Click button_Click2 = new Button_Click();
//        button_Click2.setName("我要借书");
//        button_Click2.setKey("btn_2");
//        button_Click2.setType("scancode_waitmsg");
//
//        Button_Click button_Click3 = new Button_Click();
//        button_Click3.setName("我要还书");
//        button_Click3.setKey("btn_3");
//        button_Click3.setType("scancode_waitmsg");
//
//        Button_Click button_Click4 = new Button_Click();
//        button_Click4.setName("管理员确认");
//        button_Click4.setKey("btn_4");
//        button_Click4.setType("scancode_waitmsg");
//        Button[] suButtons = new Button[4];
//        suButtons[0] = button_Click;
//        suButtons[1] = button_Click2;
//        suButtons[2] = button_Click3;
//        suButtons[3] = button_Click4;
//        Button_Click button_Click5 = new Button_Click();
//        button_Click5.setName("实验室图书");
//        button_Click5.setSub_button(suButtons);//将二级菜单装入一级菜单
//        //装载 按钮的数组
//        Button[] buttons = new Button[1];
//
//        buttons[0] = button_Click5;
//
//
//        menu.setButtons(buttons);
        return menu;

    }
}
