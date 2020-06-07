package StudyDay18;


import java.util.LinkedList;
import java.util.Queue;

public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return isMirrorSame(root,root);
    }

    //递归
    public boolean isMirrorSame(TreeNode rootA,TreeNode rootB){
        if (rootA == null && rootB == null){
            return true;
        }
        if (rootA == null || rootB == null){
            return false;
        }
        return (rootA.val == rootB.val) && isMirrorSame(rootA.left,rootB.right) && isMirrorSame(rootA.right,rootB.left);
    }

    //迭代
    public boolean isMirror(TreeNode root){
        if (root == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()){
            //每次出队两个节点
            TreeNode A = queue.poll();
            TreeNode B = queue.poll();
            //判断是否相等或空
            if (A == null && B == null){
                continue;
            }
            if (A == null || B == null || A.val != B.val){
                return false;
            }
            //两两入队判断
            queue.offer(A.left);
            queue.offer(B.right);
            queue.offer(A.right);
            queue.offer(B.left);
        }
        return true;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}