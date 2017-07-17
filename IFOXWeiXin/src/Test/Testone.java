package Test;

import Utils.WeiXinUtils.InitMenu;
import Utils.WeiXinUtils.WeiXinUtils;
import domain.Access_Token.Access_Token;
import net.sf.json.JSONObject;

/**
 * Created by 41988 on 2017/7/16.
 */
public class Testone {

    public static void main(String[] args) {
        Access_Token token = WeiXinUtils.getToken();
        System.out.println(token.getAccess_token());

        String menu = JSONObject.fromObject(InitMenu.initMenu()).toString();
//        String menu ="{\n" +
//                "\"button\":[\n" +
//                "{  \n" +
//                "\"type\":\"click\",\n" +
//                "\"name\":\"今日歌曲\",\n" +
//                "\"key\":\"V1001_TODAY_MUSIC\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"name\":\"菜单\",\n" +
//                "\"sub_button\":[\n" +
//                "{  \n" +
//                "\"type\":\"view\",\n" +
//                "\"name\":\"搜索\",\n" +
//                "\"url\":\"http://www.soso.com/\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"type\":\"click\",\n" +
//                "\"name\":\"赞一下我们\",\n" +
//                "\"key\":\"V1001_GOOD\"\n" +
//                "}]\n" +
//                "}]\n" +
//                "}";
        System.out.println(menu);

      //  int errorcode = WeiXinUtils.deleteMenu(token.getAccess_token());
        int errorcode = WeiXinUtils.creatMenu(token.getAccess_token(),menu);
        System.out.println("errorcode:"+errorcode);


    }
}
