package StudyDay12;

public class LRUTest {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(2,2);
        int i = cache.get(2);
        System.out.println(i);

    }
}
