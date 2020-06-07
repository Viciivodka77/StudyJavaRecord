package StudyDay18.Reflect;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

public class ReflectDemo02 {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, NoSuchFieldException {
//        demo01();
//        demo02();
        demo03();

    }


    //动态加载类名和方法
    public static void demo01() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Properties properties = new Properties();
        properties.load(new FileReader("class.txt"));
        String className = properties.getProperty("class");
        String methodName = properties.getProperty("method");
        Class<?> aClass = null;
        try {
            aClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method method = aClass.getDeclaredMethod(methodName);
        method.invoke(aClass.newInstance());

    }

    //反射可以越过泛型检查
    public static void demo02() throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        Class<?> aClass = arrayList.getClass();
        Method add = aClass.getMethod("add", Object.class);
        add.invoke(arrayList,"zs");
        System.out.println(arrayList);
    }

    //通过反射调用给类任意属性赋值
    public static void demo03() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        PropertyUtil propertyUtil = new PropertyUtil();
        propertyUtil.setProperty(person,"name","zs");
        propertyUtil.setProperty(person,"age",123);
        Student student = new Student();
        propertyUtil.setProperty(student,"name","晖哥");
        propertyUtil.setProperty(student,"score",100);
        System.out.println(person);
        System.out.println(student.getName()+"的打分：" + student.getScore() + "    牛啊！！！");
    }

}
