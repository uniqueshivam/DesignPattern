package DSAPractise;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        int ans = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty() && queue.peek() != null) {
            ans++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                queue.add(node.left);
                queue.add(node.right);

            }
        }
        return ans;
    }

    public int maxDepthRecursiveSolution(TreeNode root) {
        if(root == null)  {
            return 0;
        }

        return 1+ Math.max(maxDepthRecursiveSolution(root.left),maxDepthRecursiveSolution(root.right));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
