package StudyDay06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueOccurrences {

    public static void main(String[] args) {
        int[] arr = {1,2,2,1,1,3};
        System.out.println(uniqueOccurrences(arr));
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
//            if (map.get(i) == null){
//                map.put(i,1);
//            }else {
//                map.put(i,map.get(i) + 1);
//            }
            map.put(i,map.getOrDefault(i,0)+1);//避免一次判断
        }
        //hashSet add方法不能加入重复值 ，用此条件判重
        for (Integer value : map.values()) {
            if (!set.add(value)){
                return false;
            }
        }
        return true;
    }
}
