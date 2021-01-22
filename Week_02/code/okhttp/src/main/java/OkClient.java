import okhttp3.*;
import sun.rmi.runtime.Log;

import java.io.IOException;

/**
 * OkHttp构建的client
 *
 * @author gaodong
 * @program okhttp
 * @description okhttlp写的client访问8081端口
 * @create 2021-01-22 19:47
 * @since 1.8
 **/
public class OkClient {
    public static void main(String[] args){
        //构建OkHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder().build();
        //构建请求对象
        Request request = new Request.Builder().url("http://localhost:8801").get().build();
        //构建Call对象
        Call call = client.newCall(request);
        //异步get请求提交
        call.enqueue(new Callback(){

            public void onFailure(Call call, IOException e){
               System.out.println("onFailure:" + e.getMessage());
            }

            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("result:" + response.body().string());
            }
        });
    }
}
