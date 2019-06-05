package parser.states;

import parser.NodeType;
import parser.ParseAutomaton;
import parser.exceptions.ParserException;
import parser.nodes.TreeNode;

import java.util.Map;
import java.util.Stack;

public abstract class ParserStateImpl implements ParserState {
    private Map<NodeType, ParserState> adjacentNodes;

    @Override
    public Stack<TreeNode> match(Stack<TreeNode> treeNodes, TreeNode lookAhead, ParseAutomaton automaton) {
        for (Map.Entry<NodeType, ParserState> entry : adjacentNodes.entrySet()) {
            if (lookAhead.getNodeType() == entry.getKey()) {
                automaton.setCurrentState(entry.getValue());
                return treeNodes;
            }
        }
        throw new ParserException(
                "Invalid value: '" + lookAhead.getValue() +
                        "' at (" + lookAhead.getRow() + ", " + lookAhead.getColumn() + ")");
    }

    public void setAdjacentNodes(Map<NodeType, ParserState> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}
