package StudyDay18;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receive {
    public static void main(String[] args) {
        byte[] data = new byte[64];
        //准备接收数据的对象
        DatagramPacket dp = new DatagramPacket(data,data.length);
        DatagramSocket ds = null;
        System.out.println("准备接收数据");
        //接收数据
        try {
            ds = new DatagramSocket(9999);
            ds.receive(dp);
            //显示接收到的数据
            byte[] d = dp.getData();
            String s = new String(d,0,d.length);
            System.out.println("接收到的数据"+s);
            System.out.println("显示发送方的信息："+dp.getAddress().getHostAddress());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ds != null) ds.close();
        }
    }
}
