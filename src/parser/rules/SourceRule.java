package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class SourceRule implements Rule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        ArrayList<TreeNode> child = new ArrayList<>();
        while (!stack.empty()) {
            child.add(stack.pop());
        }
        stack.push(new NonTerminalNode(child, NodeType.SOURCE, 0, 0));
        return stack;
    }
}
