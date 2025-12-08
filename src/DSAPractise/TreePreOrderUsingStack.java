package DSAPractise;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreePreOrderUsingStack {
    public int[] preorderTraversal(TreeNode A) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(A);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.right!=null) {
                stack.push(node.right);
            }

            if(node.left!=null) {
                stack.push(node.left);
            }


        }
        int[] ans = new int[list.size()];
        for(int i = 0;i<list.size();i++) {
            ans[i] = list.get(i);
        }
        return ans;

    }
}
