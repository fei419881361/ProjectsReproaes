package Utils.MessageUtils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/16.
 */
public class XMLToMessage {
    public static Map<String,String> xmlToMap(HttpServletRequest request){
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
    private static void getEliments(List<Element> sonEliment, Map<String, String> map) {
        for (Element element : sonEliment) {
            if (element.elements().size() != 0) {
                getEliments(element.elements(), map);
            } else {
                map.put(element.getName(),element.getText());
            }
        }
    }
}
