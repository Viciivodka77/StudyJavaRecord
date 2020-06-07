package StudyDay19.rpc.Server;

public class HelloServiceImpl implements  HelloService {

    @Override
    public String sayHi(String name) {
        return ("hi," + name);
    }
}
