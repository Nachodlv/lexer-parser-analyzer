package parser.rules;

import parser.NodeType;
import parser.nodes.NonTerminalNode;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class DeclarationRule implements Rule {
    @Override
    public Stack<TreeNode> apply(Stack<TreeNode> stack) {
        TreeNode initializer = stack.peek();
        ArrayList<TreeNode> child = new ArrayList<>();
        if (initializer.getNodeType() == NodeType.INITIALIZER) {
            stack.pop(); //initializer
            child.add(initializer);
        }
        TreeNode type = stack.pop();
        child.add(0, type);
        TreeNode id = stack.pop();
        stack.pop(); //let
        NonTerminalNode node = new NonTerminalNode(child, NodeType.VARIABLE_DECLARATION, id.getRow(), id.getColumn(), id.getValue());
        stack.push(node);
        return stack;
    }
}
