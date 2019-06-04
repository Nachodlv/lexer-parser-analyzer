package parser.states;

import parser.ParseAutomaton;
import parser.exceptions.ParserException;
import parser.nodes.TreeNode;

import java.util.Map;
import java.util.Stack;

public abstract class ParserStateImpl implements ParserState {
    private Map<TreeNode, ParserState> adjacentNodes;

    @Override
    public Stack<TreeNode> match(Stack<TreeNode> treeNodes, TreeNode lookAhead, ParseAutomaton automaton) {
        for (Map.Entry<TreeNode, ParserState> entry : adjacentNodes.entrySet()) {
            if (lookAhead.getNodeType() == entry.getKey().getNodeType()) {
                automaton.setCurrentState(entry.getValue());
                return treeNodes;
            }
        }
        throw new ParserException(
                "Invalid value: " + lookAhead.getValue() +
                        "at (" + lookAhead.getRow() + ", " + lookAhead.getColumn() + ")");
    }

    public void setAdjacentNodes(Map<TreeNode, ParserState> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
