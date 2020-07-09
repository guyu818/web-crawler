package cn.guyu.crawler01;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Des 初始爬虫
 * @Author guyu
 * @Date 2020/7/9 14:55
 * @Param
 * @Return
 */

public class Crawler01 {
    public static void main(String[] args) throws IOException {
        //1.打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址，发起get请求创建httpGet对象
        HttpGet httpGet = new HttpGet("http://www.wxgy.site/guyu");
        //3.按回车，发起请求，返回响应，使用httpclient对象发起请求。
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.解析响应获取数据
        //判断状态码是不是200
        if (response.getStatusLine().getStatusCode() == 200){
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf-8");

            System.out.println(content);
        }

    }
}
