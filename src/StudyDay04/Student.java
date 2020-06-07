package StudyDay04;

public class Student {
    private String name ;
    private double javaScore;
    private double sqlScore;

    public Student(String name, double javaScore, double sqlScore) {
        this.name = name;
        this.javaScore = javaScore;
        this.sqlScore = sqlScore;
    }

    public double getSum(){
        return javaScore + sqlScore;
    }

    public double getAvg() {
        return getSum() / 2.0;
    }

}
