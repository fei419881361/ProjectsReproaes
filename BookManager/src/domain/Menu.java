package domain;

import domain.Button.Button;

/**
 * Created by 41988 on 2017/5/20.
 * 对Button的封装。
 */
public class Menu {
    private Button[] button;
    public Button[] getButton() {
        return button;
    }
    public void setButtons(Button[] button) {
        this.button = button;
    }
}
