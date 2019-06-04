package parser.states;

import parser.nodes.TreeNode;

public class DoubleMatcher implements TreeNodeMatcher {
    private TreeNode lastNode;
    private TreeNode lookAhead;

    public DoubleMatcher(TreeNode lowerNode, TreeNode lookAhead) {
        this.lastNode = lowerNode;
        this.lookAhead = lookAhead;
    }

    @Override
    public boolean match(TreeNode lastNode, TreeNode lookAhead) {

        return lookAhead.getNodeType() == this.lookAhead.getNodeType() &&
                this.lastNode.getNodeType() == lastNode.getNodeType();
    }
}
