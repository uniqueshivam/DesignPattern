package DSAPractise;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if(root.left == null && root.right == null) {
            return true;
        }
        return fun(root,new long[]{Long.MIN_VALUE});
    }


    public boolean fun(TreeNode root, long [] prev) {
        if(root == null) {
            return true;
        }


        if(!fun(root.left,prev)) {
            return false;
        }

        if(prev[0]>=root.val) {
            return false;
        }

        prev[0] = root.val;
        return fun(root.right,prev);
    }



}
