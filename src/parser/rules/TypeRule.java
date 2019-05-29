package parser.rules;

import parser.NodeType;
import parser.nodes.TreeNode;

import java.util.Stack;

public class TypeRule extends OneToOneRule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        return oneToOneRule(stack, NodeType.TYPE);
    }
}
