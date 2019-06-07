package interpreter.variables;

import parser.NodeType;
import parser.nodes.TreeNode;

public class NumberVariable implements Variable {
    private double value;

    public NumberVariable(double value) {
        this.value = value;
    }

    public NumberVariable() {
    }

    @Override
    public Variable calculateWith(Variable variable, TreeNode treeNode) {
        return variable.calculateWithNumber(this, treeNode);
    }

    @Override
    public Variable calculateWithNumber(NumberVariable numberVariable, TreeNode treeNode) {
        double secondValue = numberVariable.getValue();
        switch (treeNode.getNodeType()) {
            case PLUS:
                return new NumberVariable( secondValue + value);
            case MINUS:
                return new NumberVariable( secondValue - value);
            case DIVIDE:
                return new NumberVariable(secondValue / value);
            case MULTIPLY:
                return new NumberVariable(secondValue * value);
            case EQUALS:
                return new NumberVariable(value);
            default:
                throw new RuntimeException("Invalid operation: '" + treeNode.getValue() + "' at (" + treeNode.getRow()
                        + ", " + treeNode.getColumn() + ")");
        }
    }

    @Override
    public Variable calculateWithString(StringVariable stringVariable, TreeNode treeNode) {
        if (treeNode.getNodeType() == NodeType.PLUS) {
            return new StringVariable(stringVariable.getValue() + value);
        }
        throw new RuntimeException("Invalid operation: '" + treeNode.getValue() + "' at (" + treeNode.getRow()
                + ", " + treeNode.getColumn() + ")");
    }

    public double getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
