package domain.Message;

import java.util.Map;

/**
 * Created by 41988 on 2017/7/16.
 */
public class LinkMessage implements Message {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Title;
    private String Description;
    private String Url;

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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    private String MsgId
            ;

    @Override
    public void ComeMessage(Map<String, String> msg) {
        this.FromUserName = msg.get("FromUserName");
        this.ToUserName = msg.get("ToUserName");
        this.CreateTime = msg.get("CreateTime");
        this.MsgType = msg.get("MsgType");
        this.Title = msg.get("Title");
        this.Description = msg.get("Description");
        this.Url = msg.get("Url");
    }
}
