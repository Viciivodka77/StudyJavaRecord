package StudyDay15.decorator;

public abstract class SmartPhone implements Phone {
    private Phone phone;//装饰者 持有 主题的一个引用

    public SmartPhone() {
    }

    public SmartPhone(Phone phone){
        this.phone = phone;
    }

    //装饰者 包含原主题已有的功能
    @Override
    public void Call(){
        phone.Call();
    }



}
