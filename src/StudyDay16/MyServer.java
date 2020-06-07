package StudyDay16;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class MyServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader =null;
        OutputStream outputStream = null;
        //1.服务端准备服务(ip（本机ip）:端口)
        try {
            serverSocket = new ServerSocket(8888);
            //准备完毕 可以监听客户端请求；
            System.out.println("准备监听请求...");
            //用于监听客户端请求,一直阻塞，直到有客户端连接
            socket = serverSocket.accept();
            System.out.println("客户端连接成功");
            //2.通过socket生成inputstream/outputstream
            inputStream = socket.getInputStream();
            //字节流->转换流
            inputStreamReader = new InputStreamReader(inputStream);
            //转换流->字符流
            bufferedReader = new BufferedReader(inputStreamReader);
            String buffer = null;
            while(( buffer = bufferedReader.readLine()) != null ){
                System.out.println("I am server,接收到客户端信息是"+buffer);
            }
            socket.shutdownInput();

            //服务端做出反馈
            outputStream = socket.getOutputStream();
            outputStream.write("welcome client ....".getBytes());
            socket.shutdownOutput();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if (bufferedReader != null) bufferedReader.close();
                    if (inputStreamReader != null) inputStreamReader.close();
                    if (outputStream != null) outputStream.close();
                    if (inputStream != null) inputStream.close();
                    if (socket != null) socket.close();
                    if (serverSocket != null) serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }