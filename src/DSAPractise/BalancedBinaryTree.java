package DSAPractise;

public class BalancedBinaryTree {
    static boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        ans = true;
        getDepth(root);
        return ans;
    }


    public int getDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if(Math.abs(rightDepth-leftDepth)>1) {
            ans = false;
        }

        return 1+Math.max(leftDepth,rightDepth);
    }
}
