package cn.guyu.jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Des jsoup解析字符串
 * @Author guyu
 * @Date 2020/7/9 17:25
 * @Param
 * @Return
 */
public class JsoupTest02 {
    public static void main(String[] args) throws IOException {
        //使用工具类读取文件，获取字符串
        String content = FileUtils.readFileToString(new File("src/main/resources/html/guyu.html"), "utf8");
        Document document = Jsoup.parse(content);
        //使用标签选择器，选择title标签中的内容
        String title = document.getElementsByTag("title").first().text();

        System.out.println(title);
    }
}
