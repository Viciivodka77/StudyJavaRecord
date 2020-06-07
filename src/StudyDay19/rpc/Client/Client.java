package StudyDay19.rpc.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    //获取代表服务端的动态代理对象(HelloService)
    //serviceName:请求的接口名
    //address:待请求服务端的ip：端口
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress address){
        //newProxyInstance(a,b,c)
        //a:类加载器：需要代理哪个类，就需要将那个类的加载器传入第一个参数
        //b:需要代理的对象 具备哪些功能 say() sleep() --> 接口   ||| 一个类可以实现多个接口 单继承，多实现
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new InvocationHandler() {

            //proxy:代理的对象 method:哪个方法（参数列表）  args:参数列表
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                ObjectOutputStream out = null;
                ObjectInputStream in = null;
                Socket socket = null;
                Object result = null;
                try{
                //客户端向服务端发送接口:请求一个具体的接口
                socket = new Socket();
                //socketAddress:包含ip和端口
                socket.connect(address);
                out = new ObjectOutputStream(socket.getOutputStream());//发送：序列流（对象流）
                //接口名 、 方法名 ：writeUTF
                out.writeUTF(serviceInterface.getName());
                out.writeUTF(method.getName());
                //方法参数的类型 、方法参数列表
                out.writeObject(method.getParameterTypes());
                out.writeObject(args);
                //等待服务端接收处理
                //接收服务端处理后的返回值
                in = new ObjectInputStream(socket.getInputStream());
                result = in.readObject();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        if (in != null) in.close();
                        if (out != null) out.close();
                        if (socket != null) socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return result;
            }
        });
    }
}
