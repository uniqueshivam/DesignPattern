package DSAPractise;

public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {

        return fun(root,root.val);
    }

    public int fun(TreeNode root, int max) {
        if(root == null) {
            return 0;
        }

        int count = root.val>=max ? 1 :0;
        int maxVal = Math.max(root.val,max);

        return count+ fun(root.left, maxVal)+fun(root.right, maxVal);
    }

}
