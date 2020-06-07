package StudyDay14;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream("D://abc.txt");
            out = new FileOutputStream("D://xyz.txt");
            byte[] buf = new byte[10];
            int len = -1;
            while((len = in.read(buf)) != -1){//in ->
                System.out.println(len);
                out.write(buf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //abc.txt -> xyz.txt

    }
}
