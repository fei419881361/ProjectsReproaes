package Utils.MessageUtils;

import com.thoughtworks.xstream.XStream;
import domain.Message.News;
import domain.Message.NewsMessage;
import domain.Message.TextMessage;

/**
 * Created by 41988 on 2017/7/16.
 */
public class MessageToXML {
    public static String TextMessageToXML(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }
    public static String NewsMessageToXml(NewsMessage newsMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new News().getClass());
        return xStream.toXML(newsMessage);
    }
}
