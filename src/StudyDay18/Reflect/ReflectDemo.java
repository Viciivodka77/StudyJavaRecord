package StudyDay18.Reflect;

public class ReflectDemo {
    public static void main(String[] args) {
        //获取反射对象 （反射入口）:class, 1. class.forName(全类名) 2. XXX.class 3.对象.getClass()
        //1. class.forName(全类名)
        try {
            Class<?> personClass = Class.forName("StudyDay18.Reflect.Person");
            System.out.println(personClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2. 类名.class
        Class<Person> personClass2 = Person.class;
        System.out.println(personClass2);

        //3.对象.getClass()
        Person person =new Person();
        Class<? extends Person> personClass3 = person.getClass();
        System.out.println(personClass3);

    }
}
