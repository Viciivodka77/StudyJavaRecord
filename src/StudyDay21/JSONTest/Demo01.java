package StudyDay21.JSONTest;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Demo01 {
    //Map集合 ->JSON对象
    public static void test01(){
        Map<String,String> map = new HashMap<>();
        map.put("s01","zs");
        map.put("s02","ls");
        map.put("s03","ww");
        //map -> json
        JSONObject json = new JSONObject(map);
        System.out.println(json);
        //JSON格式{"s02":"ls","s01":"zs","s03":"ww"}
    }

    //JAVABean（普通对象） -> Json
    public static void test02(){
        Person person = new Person();
        person.setName("zs");
        person.setAge(23);
        Address address = new Address("南昌","宜春");
        person.setAddress(address);
        //Person(JavaBean) -->JSON
        JSONObject jsonObject = new JSONObject(person);
        System.out.println(jsonObject);
        //对象->json：
        /*
        {"address":{
            "schoolAddress":"宜春",
            "homeAddress":"南昌"},
        "name":"zs",
        "age":23}
         */
    }

    //String -> json
    public static void test03(){
        String str = "{\"name\":\"zs\"}";
        JSONObject jsonObject = new JSONObject(str);
        System.out.println(jsonObject);
    }

    //文件 -> Json  (文件 -> String ->Json)
    public void test04() throws IOException {
//        InputStream in = super.getClass().getClassLoader().getResourceAsStream("StudyDay21/JSONTest/per.json");
//        InputStreamReader inReader = new InputStreamReader(in, StandardCharsets.UTF_8);
//        char[] chars = new char[10];
//        int len = -1;
//        StringBuffer s = new StringBuffer();
//        while ((len = inReader.read(chars)) != -1){
//            String temp = new String(chars,0,len);
//            s.append(temp);
//        }
//        String s1 = s.toString();
//        JSONObject jsonObject = new JSONObject(s1);
//        System.out.println(jsonObject);

        //方法二  使用commons-io.jar中的方法
        String s = FileUtils.readFileToString(new File("E:\\IDEAProject\\StudyJava\\src\\StudyDay21\\JSONTest\\per.json"), StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(s);
        System.out.println(jsonObject);

    }

    //生成Json文件
    public static void test05() throws IOException {
        Map<String,Person> map = new HashMap<>();
        Person person1 = new Person("zs",23,new Address("nc","yc"));
        Person person2 = new Person("ls",22,new Address("nc","yc"));
        Person person3 = new Person("ww",21,new Address("nc","yc"));
        map.put(person1.getName(),person1);
        map.put(person2.getName(),person2);
        map.put(person3.getName(),person3);
        JSONObject jsonObject = new JSONObject(map);
        Writer w = new FileWriter("D://p.obj");
        jsonObject.write(w);
        w.close();
    }

    //json数组
    public static void test06(){
        String jsonArr = "[{\"name1\":\"zs\",\"age1\":\"22\"},{\"name2\":\"ls\",\"age2\":\"22\"},{\"name3\":\"ww\",\"age3\":\"22\"}]";
        JSONArray jsonArray = new JSONArray(jsonArr);
        Iterator<Object> iterator = jsonArray.iterator();
        Map<String,String> map = new HashMap<>();
        while(iterator.hasNext()){
            JSONObject next = (JSONObject)iterator.next();
            System.out.println(next);
            Set<String> key = next.keySet();
            for (String s : key) {
                map.put(s, (String) next.get(s));
            }
        }
        System.out.println(map);
    }


    public static void main(String[] args) throws IOException {
//        Demo01 demo01 = new Demo01();
//        demo01.test04();
        test06();
    }
}
