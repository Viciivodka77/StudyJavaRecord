package StudyDay07;

public class Dog extends Pet {
    @Override
    public void eat() {
        System.out.println(getName() + "吃狗粮");
    }
}
