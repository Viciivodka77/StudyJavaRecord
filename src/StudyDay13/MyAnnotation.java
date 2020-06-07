package StudyDay13;

public @interface MyAnnotation {
    /*
        用定义方法的形式定义了一个属性
        方法的名字就是属性的名字，方法的类型就是属性的类型
     */
    String value();
    int age() default 22;
}
