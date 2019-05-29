package parser.states;

import parser.ParseAutomaton;
import parser.nodes.TreeNode;

import java.util.Stack;

public interface ParserState {
    boolean isAccepting();
    Stack<TreeNode> match(Stack<TreeNode> treeNodes, ParseAutomaton automaton);
}
