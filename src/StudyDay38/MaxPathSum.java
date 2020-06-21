package StudyDay38;

/*
124. 二叉树中的最大路径和
给定一个非空二叉树，返回其最大路径和。
本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
该路径至少包含一个节点，且不一定经过根节点。

示例 1:
输入: [1,2,3]
       1
      / \
     2   3
输出: 6

示例 2:
输入: [-10,9,20,null,null,15,7]
   -10
   / \
  9  20
    /  \
   15   7
输出: 42

链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 */

/*题目大概意思就是:
给定一个非空二叉树,任意选择两个节点(可以是同一个节点),
两个节点必然可以通过唯一的 一个路径连接,
计算该路径上所有节点(包括这两个节点)的val值和.
返回其中的最大值
 */

public class MaxPathSum {
    public int maxPathSum(TreeNode root) {
        maxPathSumHandler(root);
        return max;

    }

    private int max = Integer.MIN_VALUE;

    private int maxPathSumHandler(TreeNode root) {
        if (root == null) return 0;
        //左右和0作比较 ， 如果为负数则 路径和必然减少 ， 所以用0代替（表示丢弃掉该路径）
        int left = Math.max(0,maxPathSumHandler(root.left));
        int right = Math.max(0,maxPathSumHandler(root.right));
        //判断修改max,直接加为左右两节点和自身的值 （left和right 若为0 则相当于丢弃，所以直接可以直接相加）
        //相当于以自身为根节点的左右两边相加是否会大于其他节点的最大值
        max = Math.max(max , root.val + left + right);
        //若不为最大值，则max无变动，查看是否需要加入其他节点（上一个/前一个），所以左和右选择最大的一侧传递。
        return root.val + Math.max(left,right);
    }

}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x;
      }
}