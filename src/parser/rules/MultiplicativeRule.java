package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MultiplicativeRule implements Rule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        ArrayList<TreeNode> child = new ArrayList<>();
        TreeNode literal = stack.pop();
        TreeNode operand = stack.peek();
        if (operand.getNodeType() == NodeType.MULTIPLY || operand.getNodeType() == NodeType.DIVIDE) {
            stack.pop();
            child.add(stack.pop());
            child.add(operand);
            child.add(literal);
            pushToStack(stack, operand, child);
        } else {
            child.add(literal);
            pushToStack(stack, literal, child);
        }
        return stack;
    }

    private void pushToStack(Stack<TreeNode> stack, TreeNode node, List<TreeNode> child) {
        stack.push(new NonTerminalNode(
                child,
                NodeType.MULTIPLICATIVE_EXPRESSION,
                node.getRow(),
                node.getColumn(),
                node.getValue()));
    }
}
