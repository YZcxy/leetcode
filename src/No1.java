import java.util.LinkedList;
import java.util.Queue;

//

/**
 * Minimum Depth of Binary Tree
 *
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes
 * along the shortest path from the root node
 * down to the nearest leaf node.
 *
 * 翻译：给一个二叉树，找到二叉树的最小深度。
 */
public class No1 {
    public int run(TreeNode root) {
        int result = 0;
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode endNode = new TreeNode(-1);
        queue.add(root);
        queue.add(endNode);
        int depth = 1;
        while (queue.size() != 0) {
            TreeNode temp = queue.poll();
            if (temp.val == -1) {
                depth++;
                queue.add(endNode);
                continue;
            } else {
                if (temp.left == null && temp.right == null) {
                    break;
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return depth;
    }
}
