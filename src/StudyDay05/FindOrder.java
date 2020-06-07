package StudyDay05;
/*
现在你总共有 n 门课需要选，记为 0 到 n-1。
在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
示例 1:
输入: 2, [[1,0]]
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2:
输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
说明:
输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。
你可以假定输入的先决条件中没有重复的边。
提示:
这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。
 */
import java.util.*;

public class FindOrder {

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] ints = HashSetDFSFindOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(ints));
    }
    //BFS
    private static int[] BFSFindOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        int[] inDegrees = new int[numCourses];//入度表
        //记录该课程的前面有几门课需要先修
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        //找到第一个课程（先修课程为0的课程）,
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;//记录可以学完的课程的数量
        int[] res = new int[numCourses];//记录可以学完的课程
        while(!queue.isEmpty()){
            //拿出第一个课程（即学习完第一个课程）
            int curr = queue.poll();
            //加入到结果中去
            res[count++] = curr;
            for (int[] prerequisite : prerequisites) {
                //根据先修课程找到后修课程
                if (prerequisite[1] == curr){
                    //减去后修课程所需要的先修课程数
                    inDegrees[prerequisite[0]]--;
                    //如果先修课程数为0，则代表该课程可以开始学习了
                    if (inDegrees[prerequisite[0]] == 0){
                        //放入队列中学习
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        if (count == numCourses){
            return res;
        }
        return  new int[0];
    }
    //邻接矩阵（数组） + dfs
    private static int[] DFSFindOrder(int numCourses, int[][] prerequisites){
        if (numCourses == 0){
            return new int[0];
        }
        //建立邻接矩阵
        int[][] graph = new int[numCourses][numCourses];
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]][prerequisite[0]] = 1;
        }
        // 记录访问状态的数组，访问过了标记 -1，正在访问标记 1，还未访问标记 0
        int[] status = new int[numCourses];
        //用栈保存访问序列
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            //如果存在环就返回
            if (!dfs(graph,status,i,stack)){
                return new int[0];
            }
        }
        //从栈中得到结果
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
    private static boolean dfs(int[][] graph , int[] mark , int i , Stack<Integer> stack){
        if (mark[i] == -1){
            return true;
        }
        // 当前节点在此次 dfs 中正在访问，说明存在环
        if (mark[i] == 1){
            return false;
        }
        mark[i] = 1;
        for (int j = 0; j < graph.length; j++) {
            //dfs访问当前课程之后的课程，是否存在环
            if (graph[i][j] == 1 && !dfs(graph,mark,j,stack)){
                return false;
            }
        }
        //访问结束
        mark[i] = -1;
        stack.push(i);
        return true;
    }

    //邻接矩阵（HashSet） + dfs
    private static int[] HashSetDFSFindOrder(int numCourses, int[][] prerequisites){
        if (numCourses == 0){
            return new int[0];
        }
        //用HashSet作为邻接矩阵
        HashSet<Integer>[] graph = new HashSet[numCourses];
        //预处理
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
        }
        //标记数组
        int[] mark = new int[numCourses];
        //结果栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!isCycle(graph,mark,i,stack)){
                return new int[0];
            }
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
    private static boolean isCycle (HashSet<Integer>[] graph , int[] mark,int i,Stack<Integer> stack){
        if (mark[i] == -1){
            return true;
        }
        if (mark[i] == 1){
            return false;
        }
        mark[i] = 1;
        for (Integer neighbor : graph[i]) {
            if (!isCycle(graph, mark, neighbor, stack)){
                return false;
            }
        }
        mark[i] = -1;
        stack.push(i);
        return true;
    }
}
