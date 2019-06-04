package parser.states;

import parser.nodes.TreeNode;

import java.util.Stack;

public class SimpleMatcher implements TreeNodeMatcher {
    private TreeNode treeNode;

    public SimpleMatcher(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public boolean match(TreeNode lastNode, TreeNode lookAhead) {
        return lookAhead.getNodeType() == this.treeNode.getNodeType();
    }
}
