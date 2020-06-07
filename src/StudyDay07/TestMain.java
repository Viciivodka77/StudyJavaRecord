package StudyDay07;

public class TestMain {
    public static void main(String[] args) {
        Master master = new Master();
        Dog dog = new Dog();
        dog.setName("dog");
        Cat cat = new Cat();
        cat.setName("cat");
        master.feed(cat);
        master.feed(dog);
    }
}
