package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AdditiveRule implements Rule {

    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        ArrayList<TreeNode> child = new ArrayList<>();
        TreeNode multiplicative = stack.pop();
        TreeNode operand = stack.peek();
        if (operand.getNodeType() == NodeType.PLUS || operand.getNodeType() == NodeType.MINUS) {
            stack.pop(); //operand
            child.add(stack.pop()); //additive
            child.add(operand);
            child.add(multiplicative);
            pushToStack(stack, operand, child);
        } else {
            child.add(multiplicative);
            pushToStack(stack, multiplicative, child);
        }
        return stack;
    }

    private void pushToStack(Stack<TreeNode> stack, TreeNode node, List<TreeNode> child) {
        stack.push(new NonTerminalNode(
                child,
                NodeType.ADDITIVE_EXPRESSION,
                node.getRow(),
                node.getColumn(),
                node.getValue()));
    }
}
