package StudyDay18;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlDemo {
    public static void main(String[] args) {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            URL url = new URL("https://www.baidu.com/");
            URLConnection urlConnection = url.openConnection();
            inputStream = urlConnection.getInputStream();
            fileOutputStream = new FileOutputStream("d://bilibili.txt");
            byte[] buf = new byte[64];
            int len = -1;
            while ((len = inputStream.read(buf)) != -1){
                fileOutputStream.write(buf,0,len);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileOutputStream != null)fileOutputStream.close();
                if (inputStream != null)inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
