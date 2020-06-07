package StudyDay04;

public class MainTest {
    public static void main(String[] args) {
        Student student1 = new Student("zs",99,97);
        Student student2 = new Student("ls",95,93);
        Student student3 = new Student("ww",98,99);
        System.out.println("第一位平均分:"+ student1.getAvg());
        System.out.println("第二位平均分:"+ student2.getAvg());
        System.out.println("第三位平均分:"+ student3.getAvg());
        Student[] students = new Student[]{student1,student2,student3};

    }
}
