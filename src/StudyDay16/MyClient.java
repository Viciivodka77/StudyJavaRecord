package StudyDay16;

import java.io.*;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream =null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        //1.客户端连接服务端
        try {
            socket = new Socket("127.0.0.1",8888);
            outputStream = socket.getOutputStream();
            outputStream.write("hello server".getBytes());
            socket.shutdownOutput();

            //接受服务端的反馈
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            while((info = bufferedReader.readLine()) != null){
                System.out.println("I am Client,得到反馈" + info);
            }
            socket.shutdownInput();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
