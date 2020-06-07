package StudyDay12;


import java.util.*;

public class ListTest {
    public static void main(String[] args) {
        List list =  new ArrayList();
        HashSet set = new HashSet();

        LinkedList list1 = new LinkedList();

        HashMap<String, Student> map = new HashMap<>();
        Student s1 = new Student("zs","xa");
        Student s2 = new Student("ls","bj");
        Student s3 = new Student("ww","sh");
        Student s4 = new Student("zl","sz");
        Student s5 = new Student("sq","tj");
        map.put("zs",s1);
        map.put("ls",s2);
        map.put("ww",s3);
        map.put("zl",s4);
        map.put("sq",s5);
        System.out.println("请输入名字");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        Set<String> names = map.keySet();
        Iterator<String> iterator = names.iterator();
        boolean isFounded = false;
        while(iterator.hasNext()){
            String next = iterator.next();
            if (next.equals(name)){
                Student student = map.get(next);
                System.out.println(student);
                isFounded = true;
                break;
            }
        }
        if (!isFounded){
            System.out.println("没有该学生");
        }

    }
}
