package interpreter.statements_interpreters;

import interpreter.Context;
import interpreter.expressions_interpreters.ExpressionInterpreterSupplier;
import interpreter.variables.Variable;
import parser.nodes.TreeNode;

public class PrintInterpreter implements StatementInterpreter {
    private ExpressionInterpreterSupplier supplier;

    public PrintInterpreter(ExpressionInterpreterSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void interpret(Context context, TreeNode treeNode) {
        TreeNode expression = treeNode.getChild().get(0);
        Variable variable = supplier.getExpressionInterpreter(expression.getNodeType()).calculate(context, expression);
        context.addOutput(variable.toString());
    }

    @Override
    public ExpressionInterpreterSupplier getSupplier() {
        return supplier;
    }
}
