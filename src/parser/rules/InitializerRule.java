package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class InitializerRule implements Rule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        TreeNode additive = stack.pop();
        stack.pop();
        ArrayList<TreeNode> child = new ArrayList<>();
        child.add(additive);
        NonTerminalNode node = new NonTerminalNode(
                child,
                NodeType.INITIALIZER,
                additive.getRow(),
                additive.getColumn(),
                additive.getValue());
        stack.push(node);
        return stack;
    }
}
