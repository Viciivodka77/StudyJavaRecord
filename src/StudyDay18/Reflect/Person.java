package StudyDay18.Reflect;

public class Person implements MyInterface {
    private String name;
    private int id;
    private int age;
    public String desc;

    public Person() {
    }

    private Person(int id) {
        this.id = id;
    }

    public Person(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public void sayHi(){
        System.out.println("I am person.....");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String getDesc(){
        System.out.println("bbbbbbbb");
        return desc;
    }

    private void setDesc(String desc){
        System.out.println("hhhhh");
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }


    public static void staticMethod(){
        System.out.println("static  Method....");
    }

    @Override
    public void interfaceMethod() {
        System.out.println("interface  Method....");
    }
}
