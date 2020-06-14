package StudyDay31.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext content = new ClassPathXmlApplicationContext("applicationContext.xml");
        //不能用接口的实现类来转换Proxy的实现类 -> 简单地说就是要用该类的接口来转换，而且必须是该类的接口
        IStudent student =(IStudent) content.getBean("student");
        A();
        student.add();
        B();
//        student.delete();
//        C();
    }

    public static void A(){
        System.out.println("执行了A方法");
    }

    public static void B(){
        System.out.println("执行了B方法");
    }

    public static void C(){
        System.out.println("执行了C方法");
    }
}
