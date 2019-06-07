package interpreter;

import interpreter.statements_interpreters.StatementInterpreterSupplier;
import parser.TreeNodeSupplier;
import parser.nodes.TreeNode;

public class InterpreterImpl implements Interpreter {
    private TreeNodeSupplier supplier;
    private Context context;
    private StatementInterpreterSupplier statementInterpreterSupplier;

    public InterpreterImpl(TreeNodeSupplier supplier, StatementInterpreterSupplier statementInterpreterSupplier) {
        this.supplier = supplier;
        this.context = new Context();
        this.statementInterpreterSupplier = statementInterpreterSupplier;
    }

    @Override
    public void interpret() {
        TreeNode tree = supplier.getTree();
        statementInterpreterSupplier.getStatementInterpreter(tree.getNodeType()).interpret(context, tree);
    }

    public Context getContext() {
        return context;
    }
}
