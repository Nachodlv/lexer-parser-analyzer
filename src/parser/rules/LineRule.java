package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class LineRule implements Rule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        stack.pop(); //semi-colon
        TreeNode line = stack.pop();
        ArrayList<TreeNode> child = new ArrayList<>();
        child.add(line);
        NonTerminalNode node = new NonTerminalNode(child, NodeType.LINE, line.getRow(), line.getColumn(), line.getValue());
        stack.push(node);
        return stack;
    }
}
