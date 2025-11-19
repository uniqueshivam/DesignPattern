package DSAPractise;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty() && queue.peek() !=null) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i<size;i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null) {
                    queue.add(node.left);
                }

                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}
