import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class UserUtils {

    public static void main(String[] args) throws Exception {
        doAddUsers();
    }

    private static void doAddUsers() throws IOException {
//        String host = "http://bs.uniseas.com.cn:10080";
//        String host = "http://sd.uniseas.com.cn:58080";
//        String host = "http://fj.uniseas.com.cn:10080";
//        String host = "http://gd.uniseas.com.cn:10080";
        String host = "http://10.0.0.4:10080";// 海南渔政、海南海监
//        String url = "http://bstv.uniseas.com.cn:18080";
//        String cookie = login(host, "w", "w");
        String cookie = login(host, "admin", "admin");
//        String cookie = login(host, "ww", "ww");
//        String prefix = "HaiNanYuZheng-";//海南渔政
//        String prefix = "HaiNanHaiJian-";//海南海监
        String prefix = "NanWeiUser-";//海南南威
//        prefix = "lantu-";
//        prefix = "hld-base-";
        addUsers(host, cookie, prefix, 1);
//        getTargetList(host, cookie);
    }

    /**
     * 获取目标列表
     *
     * @param host      主机或域名:端口
     * @param cookieStr 登录后拿到的 cookie
     */
    private static void getTargetList(String host, String cookieStr) throws IOException {
        String url = host + "/VTSTarget/targetInfo/list";
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", cookieStr);
        BasicCookieStore cookieStore = new BasicCookieStore();
        String domain = host.substring(7, host.lastIndexOf(":"));
        System.out.println("domain:" + domain);
        cookie.setDomain(domain);
        cookieStore.addCookie(cookie);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        HttpResponse response = client.execute(new HttpGet(url));
        System.out.println(StreamUtils.copyToString(response.getEntity().getContent(), Charset.forName("utf-8")));
    }

    private static void addUsers(String host, String cookieStr, String prefix, int number) throws IOException {
        String addUserUrl = "/VTSTarget/user/add";
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", cookieStr);
        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(cookie);
        String domain = host.substring(7, host.lastIndexOf(":"));
        System.out.println("domain:" + domain);
        cookie.setDomain(domain);
        for (int i = 1; i <= number; i++) {
            String userName = prefix + i;
            HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
            String getUrl = String.format(host + addUserUrl + "?userName=%s&password=%s&confirmpassword=%s&role.dbid=6&groupId=1&baseRule=all", userName, userName, userName);
            System.out.println("######## " + getUrl);
            HttpResponse response = client.execute(new HttpGet(getUrl));
            System.out.println(StreamUtils.copyToString(response.getEntity().getContent(), Charset.forName("utf-8")));
        }
    }

    /**
     * 登录接口
     *
     * @param host     主机和端口号
     * @param userName 用户名
     * @param password 密码
     * @return cookie
     */
    private static String login(String host, String userName, String password) throws IOException {
        String loginUrl = String.format(host + "/VTSTarget/user/login?username=%s&password=%s", userName, password);
        CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(loginUrl));
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        System.out.println("login resp:" + StreamUtils.copyToString(content, Charset.forName("utf-8")));
        Header[] allHeaders = response.getAllHeaders();
        for (Header header : allHeaders) {
            HeaderElement[] elements = header.getElements();
            for (HeaderElement element : elements) {
                if (element.getName().equals("JSESSIONID")) return element.getValue();// 返回 cookie
            }
        }
        return null;
    }
}
