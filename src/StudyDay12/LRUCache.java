package StudyDay12;

import java.util.HashMap;

public class LRUCache {

    //key映射到Node(key,value)
    HashMap<Integer,Node> map;
    //Node(k1,v1) <-> Node(k2,v2)
    DoubleList cache;
    //最大容量
    private int cap;

    // LRU (最近最少使用) 缓存机制
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }else{
            //将数据（key，val）提到最前，利用put
            int val = map.get(key).value;
            put(key,val);
            return val;
        }
    }

    public void put(int key, int value) {
        //新节点
        Node x = new Node(key,value);
        if (map.containsKey(key)){
            //把旧的数据删除；
            cache.remove(map.get(key));
            //将新节点x插入到开头；
            cache.addFirst(x);
            //更新map
            map.put(key,x);
        }else {
            if (cap == cache.size()){
                //删除链表的最后一个数据腾出位置；
                Node last = cache.removeLast();
                //删除map中映射到该数据的键；
                map.remove(last.key);
            }
            //将新节点x插入到开头；
            cache.addFirst(x);
            //map中新建key对新节点x的映射；
            map.put(key,x);
        }
    }
}

class Node{
    public int key,value;
    public Node next,prev;
    public Node(int k,int v){
        this.key = k;
        this.value = v;
    }
}

class DoubleList{
    private Node head,tail;//头尾虚节点
    private int size; //链表元素数
    public DoubleList(){
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        size = 0 ;
    }
    //在链表头部添加节点x
    public void addFirst(Node x){
        x.next = head.next;
        x.prev = head;
        head.next.prev = x;
        head.next = x;
        size++;
    }

    //删除链表中的x节点(x一定存在)
    public void remove(Node x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    //删除链表中最后一个节点，并返回该节点
    public Node removeLast(){
        if (tail.prev == head){
            return null;
        }
        Node last = tail.prev;
        remove(last);
        return last;
    }

    public int size(){
        return size;
    }

}
