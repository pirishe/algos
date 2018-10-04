package myjavacode;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

class Solution {
    List<Character> preorderTraversal(TreeNode root) {
        List<Character> ret = new ArrayList<Character> ();
        Stack<TreeNode> s = new Stack<> ();
        while(root != null || !s.empty()) {
            if (root != null) {
                ret.add(root.val);
                s.push(root.right);
                root = root.left;
            } else {
                root = s.pop();
            }
        }
        return ret;
    }

    List<Character> inorderTraversal(TreeNode root) {
        List<Character> ret = new ArrayList<Character> ();
        Stack<TreeNode> s = new Stack<> ();
        while(root != null || !s.empty()) {
            if (root != null) {
                s.push(root);
                root = root.left;
            } else {
                root = s.pop();
                ret.add(root.val);
                root = root.right;
            }
        }
        return ret;
    }

    List<Character> postorderTraversal(TreeNode root) {
        List<Character> ret = new ArrayList<> ();
        Stack<TreeNode> sNodes = new Stack<> ();
        Stack<Boolean> sCanGoLeft = new Stack<> ();
        boolean canGoLeft = true;
        while(root != null || !sNodes.empty()) {
            if (root != null) {
                if (canGoLeft) {
                    sNodes.push(root);
                    sCanGoLeft.push(false);
                    sNodes.push(root.right);
                    sCanGoLeft.push(true);
                    root = root.left;
                    canGoLeft = true;
                } else {
                    ret.add(root.val);
                    try {
                        root = sNodes.pop();
                        canGoLeft = sCanGoLeft.pop();
                    } catch (EmptyStackException e) {
                        root = null;
                        canGoLeft = true;
                    }
                }
            } else {
                root = sNodes.pop();
                canGoLeft = sCanGoLeft.pop();
            }
        }
        return ret;
    }
}