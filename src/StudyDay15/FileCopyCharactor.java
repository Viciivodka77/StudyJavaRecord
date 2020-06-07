package StudyDay15;

import java.io.*;

public class FileCopyCharactor {
    public static void main(String[] args) {
        //文件->内存（Reader）
        Reader reader = null;
        Writer writer = null;
        try {
            reader = new FileReader("D://abc.txt");
            writer = new FileWriter("D://xyz.txt");
            //在内存中替换占位符
            char[] buf =new char[4];
            StringBuffer sb = new StringBuffer();
            int len = -1;
            while ((len = reader.read(buf)) != -1){
                sb.append(buf,0,len);
            }
            String s = sb.toString();
            s = s.replace("{name}", "邹浩南")
                    .replace("{phone}","10000")
                    .replace("{email}","viciivodka77@outlook.com");
            writer.write(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //将替换后的内容输出到文件， 内存->文件（Write）

    }
}
