package interpreter.statements_interpreters;

import interpreter.Context;
import interpreter.expressions_interpreters.ExpressionInterpreterSupplier;
import parser.nodes.TreeNode;

import java.util.List;

public class SourceInterpreter implements StatementInterpreter {
   private StatementInterpreterSupplier supplier;
   private ExpressionInterpreterSupplier expSupplier;

    public SourceInterpreter(StatementInterpreterSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void interpret(Context context, TreeNode treeNode) {
        List<TreeNode> child = treeNode.getChild();
        for (TreeNode node :
                child) {
            TreeNode statement = node.getChild().get(0);
            supplier.getStatementInterpreter(statement.getNodeType()).interpret(context, statement);
        }
    }


    @Override
    public ExpressionInterpreterSupplier getSupplier() {
        return expSupplier;
    }

}
