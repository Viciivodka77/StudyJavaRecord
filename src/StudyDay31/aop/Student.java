package StudyDay31.aop;

public class Student implements IStudent {
    @Override
    public void add() {

        System.out.println("执行了add");
    }

    @Override
    public void delete() {
        int[] a = new int[5];
        System.out.println(a[-1]);
    }
}
