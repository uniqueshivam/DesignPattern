package DSAPractise;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class SameTree {
    static boolean ans = true;
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(p);
        queue2.add(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode first = queue1.poll();
            TreeNode second = queue2.poll();

            if (first == null && second == null) continue;

            if (first == null || second == null) return false;
            if (first.val != second.val) return false;

            queue1.add(first.left);
            queue1.add(first.right);
            queue2.add(second.left);
            queue2.add(second.right);
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }
}
