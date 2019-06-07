package interpreter.expressions_interpreters;

import interpreter.Context;
import interpreter.variables.NumberVariable;
import interpreter.variables.StringVariable;
import interpreter.variables.Variable;
import parser.nodes.TreeNode;

public class LiteralInterpreter implements ExpressionInterpreter {

    private ExpressionInterpreterSupplier supplier;

    public LiteralInterpreter(ExpressionInterpreterSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public Variable calculate(Context context, TreeNode treeNode) {
        TreeNode value = treeNode.getChild().get(0);
        switch (value.getNodeType()) {
            case NUMERIC_LITERAL:
                return new NumberVariable(Double.valueOf(treeNode.getValue()));
            case STRING_LITERAL:
                return new StringVariable(treeNode.getValue());
            case IDENTIFIER:
                return context.getVariable(treeNode.getValue());
            default:
                throw new RuntimeException("Should not get here");
        }
    }

    @Override
    public ExpressionInterpreterSupplier getSupplier() {
        return supplier;
    }
}
