package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class AssignationRule implements Rule{

    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        TreeNode initializer = stack.pop();
        TreeNode id = stack.pop();
        ArrayList<TreeNode> child = new ArrayList<>();
        child.add(initializer);
        NonTerminalNode node = new NonTerminalNode(child, NodeType.ASSIGNATION, id.getRow(), id.getColumn(), id.getValue());
        stack.push(node);
        return stack;
    }
}
