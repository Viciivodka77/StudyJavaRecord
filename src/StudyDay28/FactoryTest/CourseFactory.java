package StudyDay28.FactoryTest;

public class CourseFactory {
    public ICourse getCourse(String name){
        if ("java".equals(name)){
            return new JavaStudy();
        }else {
            return new SQLStudy();
        }
    }
}
