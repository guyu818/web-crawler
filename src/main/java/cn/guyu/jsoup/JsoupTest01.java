package cn.guyu.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Des jsoup解析url
 * @Author guyu
 * @Date 2020/7/9 17:25
 * @Param
 * @Return
 */
public class JsoupTest01 {
    public static void main(String[] args) throws IOException {
        //解析url地址，第一个参数是访问的url,第二个参数是访问时候的超时时间
        Document document = Jsoup.parse(new URL("http://www.wxgy.site/guyu/"), 1000);

        //使用标签选择器，选择title标签中的内容
        String title = document.getElementsByTag("title").first().text();

        System.out.println(title);
    }
}
