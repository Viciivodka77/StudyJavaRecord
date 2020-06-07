package StudyDay06;

public class Demo01 {
    public static void main(String[] args) {
        /*
        StringBuffer相较于String
        可以在原来的内存空间中直接修改数据

        String修改需要开辟新空间
        再修改引用

        如果字符串需要大量修改则使用StringBuffer节省内存空间
         */
        StringBuffer sb = new StringBuffer("abc");//String str = "abc";
        sb.append("aaa");
        System.out.println(sb);
        sb.insert(2,"bbb");
        System.out.println(sb);
        sb.reverse();//逆序
        System.out.println(sb);
        //String -> StringBuffer
        String a = "hello";
        StringBuffer a1 = new StringBuffer(a);
        //StringBuffer -> String
//        String a2 = a1.toString();
//        System.out.println(a2);
        String a2 = a1 + "";
        System.out.println(a2 );
    }
}
