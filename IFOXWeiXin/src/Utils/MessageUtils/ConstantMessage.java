package Utils.MessageUtils;

/**
 * Created by 41988 on 2017/7/16.
 */
public class ConstantMessage {
    /**
     * 各种消息类型
     * 各种事件类型：
     * subscribe：关注
     * unsubscribe:取消关注
     */
    public static final String URLADDRESS = "http://cf82e5f7.ngrok.io/";
   // public static final String IFOXADDRESS = "http://115.29.45.115/";
    public static final String IFOXADDRESS = "http://www.soso.com/";
    public static final String appid = "wx157109197bc8e05e";
    public static final String secret = "691e04b0906f163bead24a4064df70d2";
    public static String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String  Menu_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
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
}
