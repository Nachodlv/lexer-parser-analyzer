package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LiteralRule extends OneToOneRule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        if(stack.peek().getNodeType() == NodeType.STRING_LITERAL) {
            TreeNode stringLiteral = stack.pop();
            List<TreeNode> child = new ArrayList<>();
            child.add(stringLiteral);
            String value = stringLiteral.getValue();
            NonTerminalNode literal = new NonTerminalNode(child, NodeType.LITERAL, stringLiteral.getRow(), stringLiteral.getColumn(),
                    value.substring(1).substring(0, value.length() - 2));
            stack.push(literal);
            return stack;
        }
        return oneToOneRule(stack, NodeType.LITERAL);
    }
}
