#include <iostream>
#include <vector>
#include <stack>

struct TreeNode {
    char val;
    TreeNode *left;
    TreeNode *right;
    explicit TreeNode(char x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution {
public:
    std::vector<char> preorderTraversal(TreeNode *root) {
        std::vector<char> ret;
        std::stack<TreeNode *> s;
        while (root || !s.empty()) {
            if (root) {
                ret.push_back(root->val);
                s.push(root->right);
                root = root->left;
            } else {
                root = s.top();
                s.pop();
            }
        }
        return ret;
    }

    std::vector<char> inorderTraversal(TreeNode *root) {
        std::vector<char> ret;
        std::stack<TreeNode *> sNodes;
        std::stack<bool> sCanGoLeft;
        bool canGoLeft = true;
        while (root || !sNodes.empty()) {
            if (root) {
                if (canGoLeft) {
                    sNodes.push(root->right);
                    sCanGoLeft.push(true);
                    sNodes.push(root);
                    sCanGoLeft.push(false);
                    root = root->left;
                    canGoLeft = true;
                } else {
                    ret.push_back(root->val);
                    root = sNodes.top();
                    sNodes.pop();
                    canGoLeft = sCanGoLeft.top();
                    sCanGoLeft.pop();
                }
            } else {
                root = sNodes.top();
                sNodes.pop();
                canGoLeft = sCanGoLeft.top();
                sCanGoLeft.pop();
            }
        }
        return ret;
    }
};

int main() {
    /*
     *     F
     *    / \
     *   B   G
     *  / \   \
     * A  D    I
     *   / \  /
     *  C  E H
     */
    auto *nodeF = new TreeNode('F');
    auto *nodeB = new TreeNode('B');
    auto *nodeG = new TreeNode('G');
    nodeF->left = nodeB;
    nodeF->right = nodeG;
    auto *nodeA = new TreeNode('A');
    auto *nodeD = new TreeNode('D');
    nodeB->left = nodeA;
    nodeB->right = nodeD;
    auto *nodeC = new TreeNode('C');
    auto *nodeE = new TreeNode('E');
    nodeD->left = nodeC;
    nodeD->right = nodeE;

    auto *nodeI = new TreeNode('I');
    nodeG->right = nodeI;
    auto *nodeH = new TreeNode('H');
    nodeI->left = nodeH;

    auto *s = new Solution;
    std::vector<char> ret = s->postorderTraversal(nodeF);
    for (auto i : ret) {
        std::cout << i << "\n";
    }
    return 0;
}
