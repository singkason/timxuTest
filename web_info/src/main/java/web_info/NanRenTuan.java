package web_info;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class NanRenTuan {

    static String url = "http://www.btmango.com/list/%1$s/1";

    public static void main(String[] args) {
        String fanhao = "ATFB-267";
        String myUrl1 = String.format(url, fanhao);

        String html = sendUrl(myUrl1);

        String result = subHtml(html, "/info");
        String url2 = "http://www.btmango.com" + result;
        String html2 = sendUrl(url2);

        String s = subHtml(html2, "magnet:");
        System.out.println(s.trim());
    }

    private static String sendUrl(String url) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 创建httppost    
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String responseStr = EntityUtils.toString(entity, "UTF-8");

                return responseStr;
            }

        } catch (Exception e) {
            System.out.println("出错了" + url);

        }
        return null;
    }

    private static String subHtml(String html, String startWith) {
        if (html.contains(startWith)) {
            int index = html.indexOf(startWith);

            int endIndex = html.indexOf("\"", index);

            String result = html.substring(index, endIndex);

            html = html.substring(endIndex);
            return result;
        } else {
            return null;
        }
    }

}
