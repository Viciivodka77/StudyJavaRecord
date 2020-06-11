package StudyDay29;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] T) {
        int len = T.length;
        if (len == 0) return new int[0];
        int[] res = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int currentT = T[i];
            while (!stack.isEmpty() && currentT > T[stack.peek()]){
                int prevIndex = stack.poll();
                res[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] temp = new int[]{74, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temp)));
    }
}
