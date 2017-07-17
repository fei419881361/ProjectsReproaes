package domain.Button;

/**
 * Created by 41988 on 2017/7/17.
 */
public class subButton{
    private String name;
    private Button sub_button[];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
