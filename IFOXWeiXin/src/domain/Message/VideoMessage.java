package domain.Message;

import java.util.Map;

/**
 * Created by 41988 on 2017/7/16.
 */
public class VideoMessage implements Message{
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String MediaId;
    private String ThumbMediaId;
    private String MsgId;

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

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    @Override
    public void ComeMessage(Map<String, String> msg) {
        this.FromUserName = msg.get("FromUserName");
        this.ToUserName = msg.get("ToUserName");
        this.CreateTime = msg.get("CreateTime");
        this.MsgType = msg.get("MsgType");
        this.MediaId = msg.get("MediaId");
        this.ThumbMediaId = msg.get("ThumbMediaId");
        this.MsgId = msg.get("MsgId");
    }
}
