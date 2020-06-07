package StudyDay18.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo01 {

    public static void demo01(){
        Class<?> personClass = null;
        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取所有的 公共的方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

    }

    public static void demo02(){
        //获取所有的接口
        Class<?> personClass = null;

        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");

            Class<?>[] interfaces = personClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                System.out.println(anInterface);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void demo03(){
        //获取所有的父类
        Class<?> personClass = null;

        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");

            Class<?> superclass = personClass.getSuperclass();
            System.out.println(superclass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void demo04(){
        //获取所有的构造方法
        Class<?> personClass = null;

        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");

            Constructor<?>[] constructors = personClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void demo05(){
        //获取所有的公共属性
        Class<?> personClass = null;
        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");

            Field[] fields = personClass.getFields();
            for (Field field : fields) {
                System.out.println(field);
            }
            System.out.println("=====================");
            //获取所有的属性
            Field[] declaredFields = personClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println(declaredField);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void demo06(){
        //获取当前类的所有的方法(可以拿到private方法)
        //1.只能是当前类 2.忽略访问修饰符
        Class<?> personClass = null;
        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");
            Method[] declaredMethods = personClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                System.out.println(declaredMethod);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void demo07() throws IllegalAccessException, InstantiationException {
        //获取对象实例
        Class<?> personClass = null;
        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Person person =(Person) personClass.newInstance();
        person.setName("zs");
        person.setAge(23);
        person.setId(123);
        System.out.println(person);
    }

    public static void demo08() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        //操作对象属性
        Class<?> personClass = null;
        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Person person =(Person) personClass.newInstance();
        Field id = personClass.getDeclaredField("id");
        //修改属性访问权限(private会报错)
        //使用反射时，如果因为访问修饰符限制造成异常，可以通过Field/Method.setAccessible(true)解决
        id.setAccessible(true);
        id.set(person,1);
        System.out.println(person.getId());

        System.out.println("===============");

        Method getDesc = personClass.getDeclaredMethod("getDesc", null);
        getDesc.setAccessible(true);
        getDesc.invoke(person,null);

        System.out.println("==================");
        Method setDesc = personClass.getDeclaredMethod("setDesc", String.class);
        setDesc.setAccessible(true);
        setDesc.invoke(person,"aaaa");

    }


    public static void demo09() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        //操作对象属性
        Class<?> personClass = null;
        try {
            personClass = Class.forName("StudyDay18.Reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Person person =(Person) personClass.newInstance();
        Constructor<?> constructor = personClass.getDeclaredConstructor(String.class,int.class,int.class);
        System.out.println(constructor);
        System.out.println("===============");
        Person person = (Person) constructor.newInstance("yimada",55,66);
        System.out.println(person);
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
//        demo01();
//        System.out.println("=========================");
////        demo02();
////        demo03();
////        demo04();
//        demo06();
//        System.out.println("==================");
//        demo05();
//        demo07();
//        demo08();
        demo09();
    }

}
