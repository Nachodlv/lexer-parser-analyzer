package interpreter.variables;

import parser.NodeType;
import parser.nodes.TreeNode;

public class StringVariable implements Variable {
    private String value;

    public StringVariable(String value) {
        this.value = value;
    }

    public StringVariable() {
    }

    @Override
    public Variable calculateWith(Variable variable, TreeNode treeNode) {
        return variable.calculateWithString(this, treeNode);
    }

    @Override
    public Variable calculateWithNumber(NumberVariable numberVariable, TreeNode treeNode) {
        if (treeNode.getNodeType() == NodeType.PLUS) {
            return new StringVariable(numberVariable.getValue() + value);
        }
        throw new RuntimeException("Invalid operation: '" + treeNode.getValue() + "' at (" + treeNode.getRow()
                + ", " + treeNode.getColumn() + ")");
    }

    @Override
    public Variable calculateWithString(StringVariable stringVariable, TreeNode treeNode) {
        if(treeNode.getNodeType() == NodeType.PLUS) {
            return new StringVariable(stringVariable.getValue() + value);
        } else if(treeNode.getNodeType() == NodeType.EQUALS) {
            return new StringVariable(value);
        }
        throw new RuntimeException("Invalid operation: '" + treeNode.getValue() + "' at (" + treeNode.getRow()
                + ", " + treeNode.getColumn() + ")");
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}
