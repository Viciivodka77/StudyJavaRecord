package StudyDay20.FileIO;

import java.io.*;
import java.util.Properties;

public class SplitFile {
    public static void main(String[] args) throws Exception {
        //原文件
        File resFile = new File("D://Rain On Me.mp3");
        //拆分后的目录
        File splitDir = new File("D://split");
        splitFile(resFile,splitDir);

    }

    //拆分
    private static void splitFile(File resFile, File splitDir) throws Exception {
        if (!splitDir.exists()){
            splitDir.mkdirs();
        }
        //思路：1.一个输入流，n个输出流。（注意顺序）
        InputStream in = new FileInputStream(resFile);
        OutputStream out = null;
        byte[] buf = new byte[1024*1024];//定义缓冲区为1M
        int len = -1;
        int count = 1;
        while((len = in.read(buf)) != -1){
            out = new FileOutputStream(new File(splitDir,count++ + ".part"));
            out.write(buf,0,len);
            out.close();//关闭流之前会强行清理缓冲区 或者使用 out.flush();
        }
        //拆分的时候：如何将文件名、分割的数量保存,为后续合并做准备
        //再生成一个配置文件 保存描述信息
        out = new FileOutputStream(new File(splitDir,"config.properties"));
        //方式一
//        String lineSeparator = System.getProperty("line.separator") ;
//        out.write(("filename=" + resFile.getName()).getBytes());
//        out.write(lineSeparator.getBytes());
//        out.write(("partCount=" + --count).getBytes());
//        out.close();
        //方法二:Properties,将内存中的多个属性 以key-value的形式  写到硬盘中
        Properties properties = new Properties();
        properties.setProperty("filename", resFile.getName());
        properties.setProperty("partcount", --count + "");
        //写入硬盘（保存：持久化）
        properties.store(out,"file configuration...");
        out.close();
        in.close();
    }

}
