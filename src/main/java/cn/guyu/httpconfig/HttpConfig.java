package cn.guyu.httpconfig;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Des 请求配置设置
 * @Author guyu
 * @Date 2020/7/9 17:10
 * @Param
 * @Return
 */
public class HttpConfig {
    public static void main(String[] args) {
        //1.打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址，发起get请求创建httpGet对象
        HttpGet httpGet = new HttpGet("http://www.wxgy.site/guyu/");

        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) //创建连接的最长时间，单位是毫秒
                    .setConnectionRequestTimeout(500)                   //设置获取连接的最长时间
                    .setSocketTimeout(10*1000)              //设置数据传输的最长时间，单位是毫秒
                    .build();
        //给请求设置请求信息。
        httpGet.setConfig(config);



        //3.按回车，发起请求，返回响应，使用httpclient对象发起请求。
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            //4.解析响应获取数据
            //判断状态码是不是200
            if (response.getStatusLine().getStatusCode() == 200){
                HttpEntity httpEntity = response.getEntity();
                String content = EntityUtils.toString(httpEntity, "utf-8");

                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
