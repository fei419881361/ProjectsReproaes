package domain.Message;

import java.util.Map;

/**
 * Created by 41988 on 2017/7/16.
 */
public class SmallVideoMessage implements Message{
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String MediaId;
    private String ThumbMediaId;

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    private String MsgId;

    @Override
    public void ComeMessage(Map<String, String> msg) {
        this.FromUserName = msg.get("FromUserName");
        this.ToUserName = msg.get("ToUserName");
        this.CreateTime = msg.get("CreateTime");
        this.MsgType = msg.get("MsgType");
        this.ThumbMediaId = msg.get("PicUrl");
        this.MediaId = msg.get("MediaId");
        this.MsgId = msg.get("MsgId");
    }
}
