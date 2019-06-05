package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

abstract class OneToOneRule implements Rule{

    Stack<TreeNode> oneToOneRule(Stack<TreeNode> stack, NodeType nodeType) {
        TreeNode node = stack.pop();
        ArrayList<TreeNode> child = new ArrayList<>();
        child.add(node);
        NonTerminalNode newNode = new NonTerminalNode(child, nodeType, node.getRow(), node.getColumn(), node.getValue());
        stack.push(newNode);
        return stack;
    }
}
