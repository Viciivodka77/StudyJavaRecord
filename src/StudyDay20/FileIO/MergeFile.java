package StudyDay20.FileIO;

import java.io.*;
import java.util.*;

public class MergeFile {
    public static void main(String[] args) throws IOException {
        //方法一
        /*
        //读取多个拆分文件
        List<FileInputStream> inputs = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            inputs.add(new FileInputStream("D://split/" + i + ".part"));
        }
        //指定合并后的文件输出流
        OutputStream out = new FileOutputStream("D://myNewMusic.mp3");
        byte[] buf = new byte[1024*1024];
        for (FileInputStream input : inputs) {
            int len = input.read(buf);
            out.write(buf,0,len);
        }
        out.close();
        for (FileInputStream input : inputs) {
            input.close();
        }
        */


        //方法二
        //指定拆分后的文件位置
        File splitDir = new File("D://split");
        mergeFile(splitDir);
    }

    //文件合并
    private static void mergeFile(File splitDir) throws IOException {
        List<FileInputStream> inputs = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            inputs.add(new FileInputStream("D://split/" + i + ".part"));
        }
        //多个流 -> 一个流
        Enumeration<FileInputStream> enumeration = Collections.enumeration(inputs);//List -> Enumeration
        SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);
        //指定合并后的文件输出流
        OutputStream out = new FileOutputStream("D://myNewMusic2.mp3");
        //输出
        byte[] buf = new byte[1024*1024];
        int len = -1;
        while((len = sequenceInputStream.read(buf)) != -1){
            out.write(buf,0,len);
        }
        out.close();
        sequenceInputStream.close();
    }
}
