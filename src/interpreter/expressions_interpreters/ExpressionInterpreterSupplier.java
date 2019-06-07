package interpreter.expressions_interpreters;

import parser.NodeType;

public interface ExpressionInterpreterSupplier {
    ExpressionInterpreter getExpressionInterpreter(NodeType nodeType);
}
