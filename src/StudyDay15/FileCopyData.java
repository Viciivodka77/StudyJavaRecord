package StudyDay15;

import java.io.*;

public class FileCopyData {
    public static void main(String[] args) {
        //文件->内存（Reader）
        InputStream in  = null;
        OutputStream out = null;
        InputStream dataInput = null;
        OutputStream dataOutput = null;
        try {
            in = new FileInputStream("D://ow1.png");
            out = new FileOutputStream("D://ow2.jpg");

            dataInput = new DataInputStream(in);//字节流 ->二进制流
            dataOutput = new DataOutputStream(out);//字节流 ->二进制流

            //在内存中替换占位符
            byte[] buf = new byte[10];
            int len = -1;
            while ((len = dataInput.read(buf)) != -1){
               dataOutput.write(buf,0,len);
            }



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
                if (dataOutput != null) dataOutput.close();
                if (dataInput != null) dataInput.close();
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //将替换后的内容输出到文件， 内存->文件（Write）

    }
}
