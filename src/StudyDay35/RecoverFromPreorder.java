package StudyDay35;


import java.util.Deque;
import java.util.LinkedList;

public class RecoverFromPreorder {
    public TreeNode recoverFromPreorder(String S) {
        Deque<TreeNode> deque = new LinkedList<>();
        int pos = 0;
        while (pos < S.length()){
            //找到当前的深度
            int level = 0;
            while (S.charAt(pos) == '-'){
                level++;
                pos++;
            }
            //找到当前的值
            int val = 0;
            while (pos < S.length() && Character.isDigit(S.charAt(pos))){
                val = val * 10 + (S.charAt(pos) - '0');
                pos++;
            }
            //为当前值放置树的位置
            TreeNode node = new TreeNode(val);
            if (level == deque.size()){
                if (!deque.isEmpty()){
                    deque.peek().left = node;
                }
            }else {
                while (level != deque.size()){
                    deque.pop();
                }
                deque.peek().right = node;
            }
            deque.push(node);
        }
        while (deque.size() > 1 ){
            deque.pop();
        }
        return deque.peek();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}
