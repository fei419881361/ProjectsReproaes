package Utils;

import java.util.Arrays;

/**
 * Created by 41988 on 2017/5/20.
 * signature	微信加密签名，
 * signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
 */
public class ConnectionToWeiXin {
    private static final String token = "IFOX";

    public static boolean Cheksignature(String signature,String timestamp,String nonce){

        System.out.println(signature+"-"+timestamp+"-"+nonce);
        String[] arr = new String[]{token, timestamp, nonce};
        //排序
        Arrays.sort(arr);
        //生成字符串
        StringBuffer buffer = new StringBuffer();
        for(int i =0;i<arr.length;i++){
            buffer.append(arr[i]);
        }
        Sha1 sha1 = new Sha1();
        String awString = sha1.getSha1(buffer.toString());
        boolean flag;
        //将微信传过来的加密签名（signature）同我们生成的加密字串对比，相同的话就代表这是微信后台的请求。
        flag = awString.equals(signature);
        return flag;

    }
}
