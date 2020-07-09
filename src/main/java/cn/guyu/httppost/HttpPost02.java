package cn.guyu.httppost;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Des 带参数的httppost请求
 * @Author guyu
 * @Date 2020/7/9 15:24
 * @Param
 * @Return
 */
public class HttpPost02 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //1.打开浏览器，创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址，发起get请求创建httpGet对象
        HttpPost httpPost = new HttpPost("http://www.wxgy.site/guyu/");
        //声明list集合，封装表单中的参数
        List<NameValuePair> params =new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("error","login"));
        //设置请求地址为 http://www.wxgy.site/guyu/login?error=login
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
        //创建表单的entity对象到post请求中
        httpPost.setEntity(formEntity);

        //3.按回车，发起请求，返回响应，使用httpclient对象发起请求。
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
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
