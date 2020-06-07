package StudyDay21;

import java.io.*;
import java.util.*;

public class FileSplitMerge {
    public static void main(String[] args) throws IOException {
        File fileDir = new File("D://split//");
        mergeFile(fileDir);
    }

    private static void mergeFile(File fileDir) throws IOException {
        //合并之前，读取属性
        Properties properties = getProperty(fileDir);
        String filename = properties.getProperty("filename");
        int partcount = Integer.parseInt(properties.getProperty("partcount"));
        List<FileInputStream> inputs = new ArrayList<>();
        for (int i = 1; i <= partcount; i++) {
            inputs.add(new FileInputStream(new File(fileDir,i + ".part")));
        }
        Enumeration<FileInputStream> enumeration = Collections.enumeration(inputs);
        SequenceInputStream s = new SequenceInputStream(enumeration);
        OutputStream out = new FileOutputStream("D://" + filename);
        byte[] buf = new byte[1024*1024];
        int len = -1;
        while((len = s.read(buf)) != -1){
            out.write(buf,0,len);
        }
        out.close();
        s.close();
    }

    private static Properties getProperty(File fileDir) throws IOException {
        String configFileName = fileDir.getPath() + "\\config.properties" ;
        Properties properties = new Properties();
        properties.load(new FileInputStream(configFileName));
        return properties;
    }


}
