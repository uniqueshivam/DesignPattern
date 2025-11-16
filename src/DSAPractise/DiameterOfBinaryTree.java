package DSAPractise;

public class DiameterOfBinaryTree {
    public int getDiameter(TreeNode root) {
        int max = Integer.MIN_VALUE;
        getDepthOfTree(root,max);
        return max;
    }

    private int getDepthOfTree(TreeNode root, int max) {
        if(root == null) {
            return 0;
        }

        int left = getDepthOfTree(root.left,max);
        int right = getDepthOfTree(root.right,max);

        int sum = left+right;

        if(sum>max) {
            max = sum;
        }

        return 1+Math.max(left,right);
    }
}
