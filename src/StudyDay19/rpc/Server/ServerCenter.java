package StudyDay19.rpc.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCenter implements Server {
    //map:服务端所有的可供客户端访问的接口，都注册到map中
    //key:接口的名字"HelloService" value:是真正的HelloService实现
    private static HashMap<String,Class> serviceRegister = new HashMap();
    private static int port = 9999;
    //连接池：连接池中存在多个连接对象，每一个连接对象，可以处理一个客户请求
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static boolean isRunning = false;

    public ServerCenter(int port){
        this.port = port;
    }

    //开启服务端服务
    @Override
    public void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(9999));
        } catch (IOException e) {
            e.printStackTrace();
        }
        isRunning = true;
        while(true){
            //连接池
            System.out.println("stat server...");
            Socket socket = null;
            //客户每发出一次请求，服务端则从连接池中获取一个线程对象
            try {
                 socket = server.accept();//等待客户端连接
            } catch (IOException e) {
                e.printStackTrace();
            }
            executor.execute(new serviceTask(socket));
        }

    }

    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    @Override
    public void register(Class service, Class serviceImpl) {
        serviceRegister.put(service.getName(),serviceImpl);
    }

    private static class serviceTask implements Runnable{
        private Socket socket = null;

        public serviceTask(){}

        public serviceTask(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream input  = null;
            ObjectOutputStream out = null;
            try{

                //接收到客户端连接及请求
                input  = new ObjectInputStream(socket.getInputStream());//ObjectInputStream
                //因为ObjectInputStream对发送数据的顺序严格接收，因此需要参照发送的数据顺序逐个接收
                //接口名 、 方法名 、方法参数的类型 、方法参数列表
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class[] parameterTypes = (Class[]) input.readObject();//通用改成Class， Object也可以
                Object[] arguments  =(Object[]) input.readObject();
                //根据客户请求，到map中找客户对应的接口
                Class ServiceClass = serviceRegister.get(serviceName);//HelloService
                Method method = ServiceClass.getMethod(methodName, parameterTypes);
                //执行该方法 ， 执行之后可能有返回值
                Object result = method.invoke(ServiceClass.newInstance(), arguments);
                //将方法执行完毕之后的返回值 传给客户端
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(result);
            }catch (Exception e){
                e.printStackTrace();
            }
            finally{
                try {
                    if (out != null) out.close();
                    if (input != null) input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
