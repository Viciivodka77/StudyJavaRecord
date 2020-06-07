package StudyDay18;

import java.io.IOException;
import java.net.*;


public class Send {
    public static void main(String[] args) {
        String msg = "hello,Server..";
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.length(),inetAddress,9999);
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            ds.send(dp);
            System.out.println("发送完成");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ds != null) ds.close();
        }

    }
}
