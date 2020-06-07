package StudyDay15.decorator;

public class ConcreteSmartPhone1 extends SmartPhone {
    public ConcreteSmartPhone1(Phone phone) {
        super(phone);
    }

    @Override
    public void Call() {
        super.Call();
        //额外功能
        changeColor();
    }

    public void changeColor(){
        System.out.println("智能变色");
    }
}
