package interpreter.expressions_interpreters;

import parser.NodeType;

import java.util.Map;

public class ExpressionInterpreterSupplierImpl implements ExpressionInterpreterSupplier {
    private Map<NodeType, ExpressionInterpreter> interpreters;

    public ExpressionInterpreterSupplierImpl(Map<NodeType, ExpressionInterpreter> interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public ExpressionInterpreter getExpressionInterpreter(NodeType nodeType) {
        return interpreters.get(nodeType);
    }
}
