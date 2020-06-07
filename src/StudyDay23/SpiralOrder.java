package StudyDay23;

/*
面试题29. 顺时针打印矩阵
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */

public class SpiralOrder {
    //收缩边界计算
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];//特解
        int l = 0,r = matrix[0].length-1 , t = 0 ,b = matrix.length-1;//初始化边界
        int count = 0;
        int[] res = new int[(r+1)*(b+1)];
        while(true){
            for (int i = l; i <= r ; i++) {//left -> right
                res[count++] = matrix[t][i];
            }
            if (++t > b) break;//收缩边界
            for (int i = t; i <= b ; i++) {//top -> bottom
                res[count++] = matrix[i][r];
            }
            if (--r < l) break;//收缩边界
            for (int i = r; i >= l; i--) {//right -> left
                res[count++] = matrix[b][i];
            }
            if (--b < t) break;
            for (int i = b; i >= t; i--) { //bottom -> top
                res[count++] = matrix[i][l];
            }
            if (++l > r) break;
        }
        return res;
    }
}
