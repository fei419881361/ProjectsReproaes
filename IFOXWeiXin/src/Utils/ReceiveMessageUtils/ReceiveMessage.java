package Utils.ReceiveMessageUtils;

import Utils.MessageUtils.ConstantMessage;
import Utils.MessageUtils.SendMessage.SendMessage;
import Utils.MessageUtils.XMLToMessage;
import domain.Message.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/16.
 */
public class ReceiveMessage {

    public static void ReceiveMessage(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> map = XMLToMessage.xmlToMap(request);

        String msgType = map.get("MsgType");
        switch (msgType){
            case ConstantMessage.MESSAGE_TEXT:
                TextMessage textMessage = new TextMessage();
                textMessage.ComeMessage(map);
                if (textMessage.getContent().equals("2")){
                    SendMessage.SendNewsMessage(map,response);
                }
               // SendMessage.SendSubscribesMessage(map,response);
                break;
            case ConstantMessage.MESSAGE_EVENT_SUBSCRIBE:
                SendMessage.SendSubscribesMessage(map,response);
                break;

            case ConstantMessage.MESSAGE_EVENT:
                break;
            case ConstantMessage.MESSAGE_IMAGE:
                break;
        }
    }
}
