package StudyDay17;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0){
            return 0;
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            //向左找
            int left = i;
            int tempHeight = heights[i];
            while (left > 0 && heights[left -1] >= tempHeight){
                left -- ;
            }
            //向右找
            int right = i;
            while (right < len - 1 && heights[right + 1] >= tempHeight){
                right ++;
            }
            int width = right - left + 1;
            res = Math.max(res,width * tempHeight);
        }
        return res;
    }

    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        //特解
        if (len == 0){
            return 0;
        }
        if (len == 1){
            return heights[0];
        }
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0;i < len; i++){
            //有可能不止一个柱形的最大宽度可以被计算出来
            while(!stack.isEmpty() && heights[i] < heights [stack.peekLast()]){
                int curHeight = heights[stack.pollLast()];
                while(!stack.isEmpty() && heights[stack.peekLast()] == curHeight){
                    stack.pollLast();
                }

                int curWidth;
                if (stack.isEmpty()){
                    curWidth = i;
                } else {
                    curWidth = i - stack.peekLast() - 1;
                }
                res = Math.max(res,curHeight * curWidth);
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()){
            int curHeight = heights[stack.pollLast()];
            while(!stack.isEmpty() && heights[stack.peekLast()] == curHeight){
                stack.pollLast();
            }
            int curWidth;
            if (stack.isEmpty()){
                curWidth = len ;
            } else {
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res,curHeight * curWidth);
        }
        return res;
    }

}
