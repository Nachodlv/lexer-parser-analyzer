package parser.nodes;

import parser.NodeType;
import parser.nodes.TreeNode;

import java.util.List;

public class NonTerminalNode implements TreeNode {
    private List<TreeNode> child;
    private NodeType nodeType;
    private int row;
    private int column;
    private String value;

    public NonTerminalNode(List<TreeNode> child, NodeType nodeType, int row, int column) {
        this.child = child;
        this.nodeType = nodeType;
        this.row = row;
        this.column = column;
    }

    public NonTerminalNode(List<TreeNode> child, NodeType nodeType, int row, int column, String value) {
        this.child = child;
        this.nodeType = nodeType;
        this.row = row;
        this.column = column;
        this.value = value;
    }

    @Override
    public List<TreeNode> getChild() {
        return child;
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
}
