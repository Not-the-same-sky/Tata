package Utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import Bean.MessageType;
import Bean.TuLingMessage;
import Bean.TuLingResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 不一样的天空 on 2016/8/27.
 */
public class GetReponseFromTuling {
    private static final String TULING_URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "935d360d85127be8c9feafbf06f77f2d";//"0d446d56f40edf6a68c679bc79b12780";
    public static TuLingMessage sendMessage(String msg)
    {
        TuLingMessage tuLingMessage = new TuLingMessage();//设置一个ChatMessage对象
        String jsonRes = getResponse(msg);//jsonRes接受服务器得到的信息
        Gson gson = new Gson();//用来把字符串转化成对象
        TuLingResponse result = null;
        try
        {
            result = gson.fromJson(jsonRes, TuLingResponse.class);
            String content=result.getText();
            if (result.getUrl()!=null) {
                content+=result.getUrl();
            }
            tuLingMessage.setMsg(content);
        } catch (Exception e)
        {
            tuLingMessage.setMsg("服务器繁忙，请稍候再试");
        }
        tuLingMessage.setDate(System.currentTimeMillis());
        tuLingMessage.setMessageType(MessageType.RECEIVE_TEXT);
        return tuLingMessage;
    }
    private static String getResponse(String msg){
        try {
            URL url=new URL(setParams(msg));
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(url)
                    .build();
            Response response=client.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    //获取请求参数
    private static String setParams(String msg)
    {
        String url = "";
        try {
            url = TULING_URL + "?key=" + API_KEY + "&info="
                    + URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
