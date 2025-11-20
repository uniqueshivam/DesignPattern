package DSAPractise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        fun(root,ans);
        return ans;
    }

    public void fun(TreeNode root,List<Integer> ans) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty() && queue.peek()!=null) {
            int size = queue.size();
            for(int i = 0;i<size;i++) {
                TreeNode node = queue.poll();
                if(i == size-1) {
                    ans.add(node.val);
                }

                if(node.left!=null){
                    queue.add(node.left);
                }

                if(node.right!=null) {
                    queue.add(node.right);
                }
            }

        }
    }
}
