package domain.Message;

import java.util.Map;

/**
 * Created by 41988 on 2017/7/16.
 */
public class LocationMessage implements Message {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Location_X;
    private String Location_Y;
    private String Scale;
    private String Label;

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

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    private String MsgId;

    @Override
    public void ComeMessage(Map<String, String> msg) {
        this.FromUserName = msg.get("FromUserName");
        this.ToUserName = msg.get("ToUserName");
        this.CreateTime = msg.get("CreateTime");
        this.MsgType = msg.get("MsgType");
        this.Location_X = msg.get("Location_X");
        this.Location_Y = msg.get("Location_Y");
        this.Scale = msg.get("Scale");
        this.Label = msg.get("Label");
    }
}
