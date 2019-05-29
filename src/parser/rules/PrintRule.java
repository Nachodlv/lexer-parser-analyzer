package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class PrintRule implements Rule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        stack.pop(); //parenthesis
        TreeNode additive = stack.pop();
        stack.pop(); //parenthesis
        TreeNode print = stack.pop();

        ArrayList<TreeNode> child = new ArrayList<>();
        child.add(additive);
        NonTerminalNode node = new NonTerminalNode(child, NodeType.PRINT_DECLARATION, print.getRow(), print.getColumn(), print.getValue());
        stack.push(node);
        return stack;
    }
}
