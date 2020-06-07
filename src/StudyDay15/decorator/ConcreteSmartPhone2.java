package StudyDay15.decorator;

public class ConcreteSmartPhone2 extends SmartPhone {
    public ConcreteSmartPhone2(Phone phone) {
        super(phone);
    }

    @Override
    public void Call() {
        super.Call();
        //额外功能
        changeSize();
    }

    public void changeSize(){
        System.out.println("智能改变尺寸");
    }
}
