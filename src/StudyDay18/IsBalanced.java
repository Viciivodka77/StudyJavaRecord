package StudyDay18;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class IsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        return Math.abs(Depth(root.left) - Depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int Depth(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(Depth(root.left) , Depth(root.right)) + 1;
    }

    public boolean isBalanced1(TreeNode root) {
        return recur(root) != -1 ;
    }

    private int recur(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = recur(root.left);
        if (left == -1){
            return -1;
        }
        int right = recur(root.right);
        if (right == -1){
            return -1;
        }
        return Math.abs(left-right) < 2 ? Math.max(left,right) + 1 : -1;
    }


}
