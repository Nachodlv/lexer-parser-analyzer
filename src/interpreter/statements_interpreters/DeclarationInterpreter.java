package interpreter.statements_interpreters;

import interpreter.Context;
import interpreter.expressions_interpreters.ExpressionInterpreterSupplier;
import interpreter.variables.NumberVariable;
import interpreter.variables.StringVariable;
import interpreter.variables.Variable;
import parser.nodes.TreeNode;

import java.util.List;

public class DeclarationInterpreter implements StatementInterpreter {
    private ExpressionInterpreterSupplier supplier;

    public DeclarationInterpreter(ExpressionInterpreterSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void interpret(Context context, TreeNode treeNode) {
        List<TreeNode> child = treeNode.getChild();
        TreeNode type = child.get(0).getChild().get(0);
        TreeNode initializer = child.get(1);
        TreeNode equals = initializer.getChild().get(0);
        TreeNode expression = initializer.getChild().get(1);
        Variable variable = supplier.getExpressionInterpreter(expression.getNodeType()).calculate(context, expression);
        switch (type.getNodeType()) {
            case NUMBER:
                context.declareVariable(treeNode.getValue(), new NumberVariable().calculateWith(variable, equals));
                break;
            case STRING:
                context.declareVariable(treeNode.getValue(), new StringVariable().calculateWith(variable, equals));
                break;
        }
    }

    @Override
    public ExpressionInterpreterSupplier getSupplier() {
        return supplier;
    }
}
