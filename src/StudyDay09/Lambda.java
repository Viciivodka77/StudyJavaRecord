package StudyDay09;

public class Lambda {
    public static void test01(){
        new Thread(()-> System.out.println("run...")).start();
    }

    public static void main(String[] args) {
        test01();
    }
}
