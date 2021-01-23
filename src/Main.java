import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi!");
        readHtml();
    }

    public static void readHtml() {
        //https://www.cnblogs.com/zhangyinhua/p/8037599.html
        String fileName = "test_html";
        File file = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String html = null;

        try {
            file =  new File(Main.class.getResource(fileName).toURI());
            reader = new BufferedReader(new FileReader(file));

            String curLine = null;
            while ((curLine = reader.readLine()) != null) {
                sb.append(curLine);
            }

            html = sb.toString();
            reader.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }

        }

        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        Elements elFormItems = doc.getElementsByTag("el-form-item");

        System.out.println(doc.toString());

        for(Element el: elFormItems) {
            System.out.println("====================");
            Elements subEls = el.getElementsByTag("el-input");
            System.out.println("el: " + el.toString());
            System.out.println("subEls: " + subEls.select("el-input").attr("v-model"));
        }
    }
}
