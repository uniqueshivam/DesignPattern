package DSAPractise;

public class KthSmallestNumberInBST {
    static int count = 0;
    static int ans = 0;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        fun(root,k,new long[]{Long.MIN_VALUE});
        return ans;
    }

    public void fun(TreeNode root, int k, long[] prev) {
        if(root == null) {
            return;
        }

        fun(root.left,k,prev);

        if(prev[0]< root.val) {
            count++;
        }
        prev[0] = root.val;;

        if(count == k) {
            ans = root.val;
            return;
        }

        fun(root.right,k,prev);
    }
}
