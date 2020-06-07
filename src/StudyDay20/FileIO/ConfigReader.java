package StudyDay20.FileIO;

import java.io.*;

public class ConfigReader {
    public static void main(String[] args) throws IOException {
        File configFile = new File("D://split/9.config ");
        readConfig(configFile);
    }

    private static void readConfig(File configFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        String line = null;
        while((line = reader.readLine()) != null){
//            if (line.indexOf("#") != 0){
//                String[] arr = line.split("=");
//                System.out.println(arr[0] + ","+ arr[1]);
//            }
            String[] arr = line.split("=");
            if (line.startsWith("filename")){
                System.out.println("filename = " + arr[1]);
            }else if (line.startsWith("partcount")){
                System.out.println("partcount = " + arr[1]);
            }else {
                System.out.println("未处理...");
            }
        }
        reader.close();
    }
}
