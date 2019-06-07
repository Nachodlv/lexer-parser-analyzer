package interpreter.statements_interpreters;

import interpreter.Context;
import interpreter.expressions_interpreters.ExpressionInterpreterSupplier;
import parser.nodes.TreeNode;

public interface StatementInterpreter {
    void interpret(Context context, TreeNode treeNode);
    ExpressionInterpreterSupplier getSupplier();
}
