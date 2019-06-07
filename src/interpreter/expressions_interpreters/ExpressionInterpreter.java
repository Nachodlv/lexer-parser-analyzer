package interpreter.expressions_interpreters;

import interpreter.Context;
import interpreter.variables.Variable;
import parser.nodes.TreeNode;

public interface ExpressionInterpreter {
    Variable calculate(Context context, TreeNode treeNode);
    ExpressionInterpreterSupplier getSupplier();
}
