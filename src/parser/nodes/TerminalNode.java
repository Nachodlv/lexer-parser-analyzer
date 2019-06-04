package parser.nodes;

import parser.NodeType;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TerminalNode implements TreeNode {
    private NodeType nodeType;
    private int column;
    private int row;
    private String value;

    public TerminalNode(NodeType nodeType, int column, int row, String value) {
        this.nodeType = nodeType;
        this.column = column;
        this.row = row;
        this.value = value;
    }

    public TerminalNode(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public List<TreeNode> getChild() {
        return new ArrayList<>();
    }

    @Override
    public NodeType getNodeType() {
        return nodeType;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "( " + nodeType.toString() + ", " + value + ")";
    }
}
