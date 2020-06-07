package StudyDay15;

import java.io.*;

public class FileCopyCharactorBuffered {
    public static void main(String[] args) {
        //文件->内存（Reader）
        Reader reader = null;
        Writer writer = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            reader = new FileReader("D://abc.txt");
            writer = new FileWriter("D://xyz.txt");

            br = new BufferedReader(reader);
            bw = new BufferedWriter(writer);

            //在内存中替换占位符
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null){
               sb.append(line);
            }


            String s = sb.toString();
            s = s.replace("{name}", "邹浩南")
                    .replace("{phone}","10000")
                    .replace("{email}","viciivodka77@outlook.com");

            bw.write(s);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            /*
                先关出，再关入
                从外往内关
             */

            try {
                if (bw != null) bw .close();
                if (br != null) br .close();
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //将替换后的内容输出到文件， 内存->文件（Write）

    }
}
