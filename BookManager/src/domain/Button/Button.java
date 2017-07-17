package domain.Button;

/**
 * Created by 41988 on 2017/5/20.
 * button 父类。
 * 提取出来的公共属性。
 */
public class Button {
    private String type;
    private String name;
    private Button sub_button[];//二级菜单

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Button[] getSub_button() {
        return sub_button;
    }
    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
