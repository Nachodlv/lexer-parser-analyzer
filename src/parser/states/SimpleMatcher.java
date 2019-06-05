package parser.states;

import parser.NodeType;

public class SimpleMatcher implements TreeNodeMatcher {
    private NodeType treeNode;

    public SimpleMatcher(NodeType treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public boolean match(NodeType lastNode, NodeType lookAhead) {
        return lastNode == this.treeNode;
    }
}
