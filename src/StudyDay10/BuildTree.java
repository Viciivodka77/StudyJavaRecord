package StudyDay10;


import java.util.HashMap;
import java.util.Map;


public class BuildTree {
    private Map<Integer,Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft , int inorderRight){
        if (preorderLeft > preorderRight){
            return null;
        }
        // 前序遍历的第一个节点就是根节点
        int preoderRoot = preorderLeft;
        int inorderRoot = indexMap.get(preorder[preoderRoot]);
        //建立树的根节点
        TreeNode root = new TreeNode(preorder[preoderRoot]);
        //得到左子树的节点数目
        int sizeLeftSubtree = inorderRoot - inorderLeft;
        //递归构造左子树，并连接到根节点
        //先序遍历中【从 左边界+1 开始到 sizeLeftSubtree】个元素对应了中序遍历中【从 左边界 到 根节点定位-1】的元素
        root.left = myBuildTree(preorder,inorder,preorderLeft+1,preorderLeft+sizeLeftSubtree,inorderLeft,inorderRoot-1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorderLeft + sizeLeftSubtree + 1, preorderRight, inorderRoot + 1, inorderRight);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i],i);
        }
        //传入最左侧和最右侧 0,n-1
        return myBuildTree(preorder,inorder,0,n-1,0,n-1);
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){val = x;}
}