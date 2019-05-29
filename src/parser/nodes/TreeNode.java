package parser.nodes;

import parser.NodeType;

import java.util.List;

public interface TreeNode {
    List<TreeNode> getChild();
    NodeType getNodeType();
    int getColumn();
    int getRow();
    String getValue();
}
