package interpreter.statements_interpreters;

import parser.NodeType;

import java.util.Map;

public class StatementInterpreterSupplierImpl implements StatementInterpreterSupplier{
    private Map<NodeType, StatementInterpreter> interpreters;

    public StatementInterpreterSupplierImpl(Map<NodeType, StatementInterpreter> interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public StatementInterpreter getStatementInterpreter(NodeType nodeType) {
        return interpreters.get(nodeType);
    }
}
