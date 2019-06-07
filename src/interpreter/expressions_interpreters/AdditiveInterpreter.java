package interpreter.expressions_interpreters;

import interpreter.Context;
import interpreter.variables.Variable;
import parser.nodes.TreeNode;

import java.util.List;

public class AdditiveInterpreter implements ExpressionInterpreter {
    private ExpressionInterpreterSupplier supplier;

    public AdditiveInterpreter(ExpressionInterpreterSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public Variable calculate(Context context, TreeNode treeNode) {
        List<TreeNode> child = treeNode.getChild();
        TreeNode firstTerm = child.get(0);
        ExpressionInterpreter additiveSupplier = supplier.getExpressionInterpreter(firstTerm.getNodeType());
        if(child.size() > 1) {
            TreeNode plus = child.get(1);
            TreeNode multiplicative = child.get(2);
            return additiveSupplier
                    .calculate(context, firstTerm)
                    .calculateWith(supplier.getExpressionInterpreter(multiplicative.getNodeType())
                            .calculate(context, multiplicative), plus);
        } else {
           return additiveSupplier.calculate(context, firstTerm);
        }
    }

    @Override
    public ExpressionInterpreterSupplier getSupplier() {
        return null;
    }
}
