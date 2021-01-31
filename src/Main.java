import entity.User;
import entity.UserList;
import freemarker.template.Template;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi!");
//        readHtml();
        try {
            String html = parseTmp();
            String doc = parseHtml(html);

            System.out.println(html);
            System.out.println(doc);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String parseTmp() throws Exception{
        String fileName = "test_freemarker";
        Reader reader = new FileReader(new File(Main.class.getResource(fileName).toURI()));
        Template tmp = new Template("test", reader, null);

        UserList userList = new UserList();
        userList.setCategory("用户信息");

        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("Jonason");
        user1.setAge(16);

        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("Joseph");
        user2.setAge(18);

        users.add(user1);
        users.add(user2);

        userList.setUsers(users);

        Map<String, Object> data = new HashMap<>();
        data.put("userList", userList);
        Writer writer = new StringWriter();
        tmp.process(data, writer);
        writer.flush();

        String html = writer.toString();

        writer.close();
        reader.close();

        return html;
    }

    private static String parseHtml(String html) {
        //https://www.cnblogs.com/zhangyinhua/p/8037599.html
        Document doc = Jsoup.parseBodyFragment(html);
        Elements els = doc.getElementsByTag("tr");
        for(Element el: els) {
            //获取第一个子元素
            Element elChild = el.child(1);
            //获取元素的文本
            System.out.println(elChild.text());
            //添加属性
            el.attr("required", "true");
        }
        return doc.toString();
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
