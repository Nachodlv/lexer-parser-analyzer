package interpreter.statements_interpreters;

import parser.NodeType;

public interface StatementInterpreterSupplier {
    StatementInterpreter getStatementInterpreter(NodeType nodeType);
}
