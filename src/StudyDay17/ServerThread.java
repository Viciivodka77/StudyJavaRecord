package StudyDay17;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        ObjectInputStream ois = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            ois = new ObjectInputStream(inputStream);
            Student student = (Student) ois.readObject();
            System.out.println(student);
            socket.shutdownInput();

            outputStream = socket.getOutputStream();
            outputStream.write("服务端已收到".getBytes());
            socket.shutdownOutput();;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream != null) outputStream.close();
                if (ois != null) ois.close();
                if (inputStream != null) inputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
