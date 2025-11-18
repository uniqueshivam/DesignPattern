package DSAPractise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> first = new ArrayList<>();
        List<TreeNode> second = new ArrayList<>();
        getPath(root,p, first);
        getPath(root,q, second);
        Collections.reverse(first);
        Collections.reverse(second);

        System.out.println(first.size()+" "+second.size());
        int pointer = 0;
        boolean flag = false;
        while(pointer<first.size() && pointer<second.size()) {
            if(first.get(pointer) == second.get(pointer)) {
                pointer++;
                continue;

            }
            pointer--;
            flag = true;
            break;
        }
        if(!flag) {
            pointer--;
        }

        return pointer<first.size()-1 ? first.get(pointer) : second.get(pointer);

    }

    public boolean getPath(TreeNode root, TreeNode subRoot, List<TreeNode> list) {
        if(root == null) {
            return false;
        }

        if(subRoot.val == root.val) {
            list.add(subRoot);
            return true;
        }

        boolean left  = getPath(root.left,subRoot,list);
        boolean right = getPath(root.right,subRoot, list);

        if(left || right) {
            list.add(root);
        }
        return left || right;
    }

    public TreeNode optimised(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
