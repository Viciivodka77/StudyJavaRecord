package StudyDay14;

import java.util.HashMap;

public class SubarraysDivByK {
    //Solution.No 1 前缀和
    public int subarraysDivByK1(int[] A, int K) {
        int len = A.length;
        int count = 0;
        int[] preSum = new int[len+1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i+1] = preSum[i] + A[i];
        }
        for (int i = 0; i < len+1; i++) {
            for (int j = i+1; j < len+1; j++) {
                if ((preSum[j] - preSum[i]) % K == 0){
                    count ++;
                }
            }
        }
        return count;
    }

    //Solution.No 2 前缀和 + 同余定理
    public int subarraysDivBy2(int[] A, int K) {
        int count = 0;
        HashMap<Integer, Integer> prefixMap = new HashMap<>();//key-value:前缀和-出现次数
        int preSum = 0;
        prefixMap.put(0,1);
        for (int i : A) { //循环记录出现次数
            preSum += i;
            int mod = Math.floorMod(preSum, K);
            Integer same = prefixMap.getOrDefault(mod, 0);
            count += same;
            prefixMap.put(mod,same + 1);
        }
        return count;
    }

    /*
    对于整数 aaa，bbb 来说，取模运算和取余运算的过程相同：
    求整数商：c=a/b
    计算模或者余数：r=a−c×b
    取模运算结果的符号和 b 一致，取余运算结果的符号和 a 一致。
    Math.floorMod(a,b)                          a % b
     */

    //Solution.No 3 同余定理优化
    public int subarraysDivBy3(int[] A, int K) {
        int count = 0,sum = 0;
        int[] m = new int[K];//记录前缀和中 相同的模 出现的个数 所以容量为 K
        m[0] = 1;//排除本身为K的倍数的情况
        for (int i : A) {//两个数模 K 的结果相等, 其差能被 K 整除
            sum += i;
            int remainder = Math.floorMod(sum,K);
            count += m[remainder];
            m[remainder]++;
        }
        return count;
    }
}
