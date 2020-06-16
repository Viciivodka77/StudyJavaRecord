package StudyDay33;

import java.util.Deque;
import java.util.LinkedList;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder("[");
        Deque<TreeNode> deque =new LinkedList<>();
        deque.push(root);
        while(!deque.isEmpty()){
            TreeNode curNode = deque.pollFirst();
            if (curNode != null){
                deque.add(curNode.left);
                deque.add(curNode.right);
                sb.append(curNode.val);
                sb.append(",");
            }else {
                sb.append("null,");
            }
        }
        sb.setLength(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        TreeNode root = getNode(nodes[0]);
        if (root == null) return null;
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode parent = root;
        boolean isLeft = true;
        for (int i = 1; i < nodes.length; i++) {
            TreeNode curNode = getNode(nodes[i]);
            if (isLeft){
                parent.left = curNode;
            }else {
                parent.right = curNode;
            }
            if (curNode != null){
                deque.add(curNode);
            }
            isLeft = !isLeft;
            if (isLeft){
                parent = deque.pollFirst();
            }
        }
        return  root;
    }

    private TreeNode getNode(String str){
        TreeNode node = null;
        if (!"null".equals(str)) {
            node = new TreeNode(Integer.parseInt(str));
        }
        return node;
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}