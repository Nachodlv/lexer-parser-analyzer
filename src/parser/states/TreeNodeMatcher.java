package parser.states;

import parser.nodes.TreeNode;

import java.util.Stack;

public interface TreeNodeMatcher {
    boolean match(TreeNode lastNode, TreeNode lookAhead);
}
