package StudyDay17;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);

            while (true){
                Socket socket = serverSocket.accept();//阻塞
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
