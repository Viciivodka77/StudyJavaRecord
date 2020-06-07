package StudyDay19.rpc.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Server {

    void start();
    void stop();
    //注册服务
    void register(Class service, Class serviceImpl);

}
