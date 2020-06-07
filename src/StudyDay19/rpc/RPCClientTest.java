package StudyDay19.rpc;

import StudyDay19.rpc.Client.Client;
import StudyDay19.rpc.Server.HelloService;


import java.net.InetSocketAddress;


public class RPCClientTest {
    public static void main(String[] args) throws ClassNotFoundException {
        HelloService helloService = Client.getRemoteProxyObj(Class.forName("StudyDay19.rpc.Server.HelloService"), new InetSocketAddress("127.0.0.1",9999));
        String wtf = helloService.sayHi("wtf");
        System.out.println(wtf);
    }
}
