package StudyDay07;

public class Master {
    public void feed(Pet pet){
        System.out.println("喂"+pet.getName());
        pet.eat();
    }
}
