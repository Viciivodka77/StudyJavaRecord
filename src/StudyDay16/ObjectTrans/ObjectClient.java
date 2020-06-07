package StudyDay16.ObjectTrans;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ObjectClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        ObjectOutputStream oos = null;
        try {
             socket = new Socket("localhost", 8888);

             Student student = new Student(1001,"zs",23);

            outputStream = socket.getOutputStream();
            //将输出流->对象流
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(student);//发送对象
            socket.shutdownOutput();
            System.out.println("发送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) oos.close();
                if (outputStream != null) outputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
