package parser.states;

import parser.NodeType;
import parser.nodes.TreeNode;

import java.util.Stack;

public interface TreeNodeMatcher {
    boolean match(NodeType lastNode, NodeType lookAhead);
}
