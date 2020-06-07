package StudyDay15.decorator;

public class Test {
    public static void main(String[] args) {
        ConcretePhone phone = new ConcretePhone();
        phone.Call();
        System.out.println("-----------------");
        ConcreteSmartPhone1 smartPhone1 = new ConcreteSmartPhone1(phone);
        smartPhone1.Call();
        System.out.println("-----------------");
        ConcreteSmartPhone2 smartPhone2 = new ConcreteSmartPhone2(phone);
        smartPhone2.Call();
        System.out.println("-----------------");
        SmartPhone smartphone = new ConcreteSmartPhone2(new ConcreteSmartPhone1(phone));
        smartphone.Call();
    }
}
