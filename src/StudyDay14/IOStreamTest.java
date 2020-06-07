package StudyDay14;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class IOStreamTest {
    public static void main(String[] args)  {
//        File file = new File("D://IOTest.txt");
//        if (file.exists()){
//            file.delete();
//            System.out.println("删除成功");
//        }else {
//            file.createNewFile();
//            System.out.println("创建成功");
//        }
        InputStream in = null;
        try {
            in = new FileInputStream("D://abc.txt");
            InputStreamReader in1 = new InputStreamReader(in, StandardCharsets.UTF_8);
            char[] chars = new char[in.available()];//获取文件大小 file.length()

            in1.read(chars,0,in.available());
//            in1.read(chars);//将文件abc.txt内容读取到buf中
            //chars :btye[] -> String
            System.out.println(new String(chars));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
