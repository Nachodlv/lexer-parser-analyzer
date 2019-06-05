package parser.states;

import parser.NodeType;
import parser.nodes.TreeNode;

public class DoubleMatcher implements TreeNodeMatcher {
    private NodeType lastNode;
    private NodeType lookAhead;

    public DoubleMatcher(NodeType lowerNode, NodeType lookAhead) {
        this.lastNode = lowerNode;
        this.lookAhead = lookAhead;
    }

    @Override
    public boolean match(NodeType lastNode, NodeType lookAhead) {
        return lookAhead == this.lookAhead &&
                this.lastNode == lastNode;
    }
}
