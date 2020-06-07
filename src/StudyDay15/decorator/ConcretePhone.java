package StudyDay15.decorator;

public class ConcretePhone implements Phone {
    @Override
    public void Call() {
        System.out.println("打电话");
    }
}
