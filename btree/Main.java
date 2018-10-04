package myjavacode;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        /*
         *     F
         *    / \
         *   B   G
         *  / \   \
         * A  D    I
         *   / \  /
         *  C  E H
         */
        TreeNode nodeF = new TreeNode('F');
        TreeNode nodeB = new TreeNode('B');
        TreeNode nodeG = new TreeNode('G');
        nodeF.left = nodeB;
        nodeF.right = nodeG;
        TreeNode nodeA = new TreeNode('A');
        TreeNode nodeD = new TreeNode('D');
        nodeB.left = nodeA;
        nodeB.right = nodeD;
        TreeNode nodeC = new TreeNode('C');
        TreeNode nodeE = new TreeNode('E');
        nodeD.left = nodeC;
        nodeD.right = nodeE;
        TreeNode nodeI = new TreeNode('I');
        nodeG.right = nodeI;
        TreeNode nodeH = new TreeNode('H');
        nodeI.left = nodeH;
        Solution s = new Solution();
        List<Character> response = s.postorderTraversal(nodeF);
        response.forEach(System.out::println);
    }
}
