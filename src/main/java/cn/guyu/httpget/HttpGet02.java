package cn.guyu.httpget;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Des 带参数的httpget请求
 * @Author guyu
 * @Date 2020/7/9 15:24
 * @Param
 * @Return
 */
public class HttpGet02 {
    public static void main(String[] args) throws Exception {
        //1.打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个uribuilder
        URIBuilder uriBuilder = new URIBuilder("http://www.wxgy.site/guyu/login");
        //设置参数,可以设置多个
        uriBuilder.setParameter("error", "login");

        //2.输入网址，发起get请求创建httpGet对象
//        访问的连接是：http://www.wxgy.site/guyu/login?error=login
        HttpGet httpGet = new HttpGet(uriBuilder.build());
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
