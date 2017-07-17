package Utils.WeiXinUtils;

import Utils.MessageUtils.ConstantMessage;
import domain.Button.*;

/**
 * Created by 41988 on 2017/7/16.
 */
public class InitMenu {
    public static Menu initMenu(){
        Menu menu = new Menu();
        Button_Click abutton_click = new Button_Click();
        abutton_click.setName("ifox简介");
        abutton_click.setType("click");
        abutton_click.setKey("ifoxjianjie");

        Button_View abutton_view = new Button_View();
        abutton_view.setName("ifox官网");
        abutton_view.setType("view");
        abutton_view.setUrl(ConstantMessage.IFOXADDRESS);

        Button_View abutton_view2 = new Button_View();
        abutton_view2.setName("ifox图书馆");
        abutton_view2.setUrl(ConstantMessage.IFOXADDRESS);
        abutton_view2.setType("view");

        Button[] abuttons = new Button[3];
        abuttons[0]=abutton_view;abuttons[1]=abutton_view2;abuttons[2]=abutton_click;

        subButton abutton = new subButton();
        abutton.setName("ifox");
        abutton.setSub_button(abuttons);
//---------------------------------------------------------
        Button_View button_view = new Button_View();
        button_view.setName("最新发布");
        button_view.setUrl(ConstantMessage.IFOXADDRESS);
        button_view.setType("view");

        Button_View button_view2 = new Button_View();
        button_view2.setName("手机应用");
        button_view2.setUrl(ConstantMessage.IFOXADDRESS);
        button_view2.setType("view");

        Button_View button_view3 = new Button_View();
        button_view3.setName("微信开发");
        button_view3.setUrl(ConstantMessage.IFOXADDRESS);
        button_view3.setType("view");

        Button_View button_view4 = new Button_View();
        button_view4.setName("微信开发");
        button_view4.setUrl(ConstantMessage.IFOXADDRESS);
        button_view4.setType("view");

        Button[] suButtons = new Button[4];
        suButtons[0] = button_view;
        suButtons[1] = button_view4;
        suButtons[2] = button_view2;
        suButtons[3] = button_view3;

        subButton button_Click5 = new subButton();
        button_Click5.setName("成功案例");
        button_Click5.setSub_button(suButtons);//将二级菜单装入一级菜单
//---------------------------------------------------------------------
        Button_Click cbutton = new Button_Click();
        cbutton.setKey("jiaruwomen");
        cbutton.setName("加入我们");
        cbutton.setType("click");

        Button_Click cbutton2 = new Button_Click();
        cbutton2.setKey("gengduogongneng");
        cbutton2.setName("更多功能");
        cbutton2.setType("click");

        Button[] cbuttons = new Button[2];
        cbuttons[0] = cbutton;
        cbuttons[1] = cbutton2;
//------------------------------------------------
        subButton cbutton3 = new subButton();
        cbutton3.setName("关于我们");
        cbutton3.setSub_button(cbuttons);

        //装载 按钮的数组
        subButton[] buttons = new subButton[3];
        buttons[0] = abutton;
        buttons[1] = button_Click5;
        buttons[2] = cbutton3;


        menu.setButton(buttons);


        return menu;

    }
}
