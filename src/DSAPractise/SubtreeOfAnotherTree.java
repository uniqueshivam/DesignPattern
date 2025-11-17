package DSAPractise;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return fun(root,subRoot);

    }

    public boolean fun(TreeNode root, TreeNode subRoot) {
        if(root == null) {
            return false;
        }

        if(isIdentical(root,subRoot)) {
            return true;
        }

        return fun(root.left,subRoot) || fun(root.right,subRoot);

    }

    public boolean isIdentical(TreeNode root, TreeNode subRoot) {
        if(root == null || subRoot == null) {
            return false;
        }

        boolean b = root.val == subRoot.val;
        return b && isIdentical(root.left,subRoot.left) && isIdentical(root.right,subRoot.right);
    }

    // This uses tree serialization technique and compares if string contains another string
    public boolean optimized(TreeNode root, TreeNode subRoot) {
        return serialize(root).contains(serialize(subRoot));
    }

    private String serialize(TreeNode node) {
        if (node == null) return "#";
        return "," + node.val + serialize(node.left) + serialize(node.right);
    }
}
