package StudyDay11;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdademo01 {

    private static void test01(){
        Predicate<Integer> p = num -> num > 10;
//        Predicate<Integer> p = (num) ->{return num > 10;};
        System.out.println(p.test(2));
    }

    private static void test02(){
        MyMath math = (a,b) -> a+b ;
        System.out.println(math.add(5,6));
    }

    private static void test03(){
        Consumer<String> consumer = x -> System.out.println("eat"+x);
        consumer.accept("hhhhh");

    }

    private static void test04(){
        Supplier<Integer> supplier = () -> (int) (Math.random()*9000+1000);
        System.out.println(supplier.get());
    }

    private static void test05(){
        Function<String,String> function = (a) -> a.toUpperCase();
        System.out.println(function.apply("someone ghost"));
    }

    private static void test06(){
        String str = upper((x) -> x.toUpperCase() , "aghost");
        System.out.println(str);
    }

    private static String upper(Function<String,String> function , String string){
        return function.apply(string);
    }

    private static void test07(){
        myPredicate((a) -> a > 100 , 200);
    }

    private static void myPredicate(Predicate<Integer> predicate, Integer num){
        System.out.println(predicate.test(num));
    }


    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
        test06();
        test07();
    }
}
