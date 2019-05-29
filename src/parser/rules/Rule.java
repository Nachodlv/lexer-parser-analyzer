package parser.rules;

import parser.nodes.TreeNode;

import java.util.Stack;

public interface Rule {
    Stack<TreeNode> apply(Stack<TreeNode> stack);
}
