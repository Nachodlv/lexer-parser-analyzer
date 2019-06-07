package interpreter.statements_interpreters;

import interpreter.Context;
import interpreter.expressions_interpreters.ExpressionInterpreterSupplier;
import interpreter.variables.Variable;
import parser.nodes.TreeNode;

public class AssignationInterpreter implements StatementInterpreter {
    private ExpressionInterpreterSupplier supplier;

    public AssignationInterpreter(ExpressionInterpreterSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void interpret(Context context, TreeNode treeNode) {
        TreeNode initializer = treeNode.getChild().get(0);
        String id = treeNode.getValue();
        TreeNode equal = initializer.getChild().get(0);
        TreeNode additive = initializer.getChild().get(1);

        Variable variable = supplier.getExpressionInterpreter(additive.getNodeType()).calculate(context, additive);
        Variable result = context.getVariable(id).calculateWith(variable, equal);
        context.reAssignVariable(id, result);
    }

    @Override
    public ExpressionInterpreterSupplier getSupplier() {
        return supplier;
    }
}
