package StudyDay19.rpc;

import StudyDay19.rpc.Server.HelloService;
import StudyDay19.rpc.Server.HelloServiceImpl;
import StudyDay19.rpc.Server.Server;
import StudyDay19.rpc.Server.ServerCenter;

public class RPCServerTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Server server = new ServerCenter(9999);
                server.register(HelloService.class, HelloServiceImpl.class);
                server.start();
            }
        }).start();


    }
}
