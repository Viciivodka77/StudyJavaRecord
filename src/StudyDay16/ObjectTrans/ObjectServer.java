package StudyDay16.ObjectTrans;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ObjectInputStream ois = null;
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("准备监听");
            socket = serverSocket.accept();
            System.out.println("连接成功");

            //接收客户端发来的对象
            inputStream = socket.getInputStream();
            ois = new ObjectInputStream(inputStream);
            try {
                Student o =(Student) ois.readObject();//读取对象
                System.out.println(o);
                socket.shutdownInput();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                if (ois != null) ois.close();
                if (inputStream != null) inputStream.close();
                if (socket != null) socket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
