package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class TypeAnnotationRule implements Rule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        TreeNode type = stack.pop();
        stack.pop();
        ArrayList<TreeNode> child = new ArrayList<>();
        child.add(type);
        NonTerminalNode node = new NonTerminalNode(
                child,
                NodeType.TYPE_ANNOTATION,
                type.getRow(),
                type.getColumn(),
                type.getValue());
        stack.push(node);
        return stack;
    }
}
