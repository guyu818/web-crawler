package cn.guyu.httpclientpool;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Des httpClient 连接池的使用
 * @Author guyu
 * @Date 2020/7/9 16:22
 * @Param 
 * @Return 
 */
public class HttpClientPool01 {
    public static void main(String[] args) {
        //创建连接池管理器发起请求
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(100);
        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);
        //使用连接池管理器发起请求
        doGet(cm);
        doGet(cm);

    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        //不是每次创建新的httpclient,而是从连接池获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet("http://www.wxgy.site/guyu");

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf8");

                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //httpclient不用关闭，由连接池来管理
//            httpClient.close();
        }

    }
}
