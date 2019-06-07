package main;

import interpreter.ContextListener;
import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import interpreter.expressions_interpreters.AdditiveInterpreter;
import interpreter.expressions_interpreters.ExpressionInterpreter;
import interpreter.expressions_interpreters.ExpressionInterpreterSupplierImpl;
import interpreter.expressions_interpreters.LiteralInterpreter;
import interpreter.statements_interpreters.*;
import parser.NodeType;
import parser.TreeNodeSupplier;

import java.util.HashMap;

class InterpreterBuilder {
    private InterpreterBuilder() {
    }

    static Interpreter buildInterpreter(TreeNodeSupplier treeNodeSupplier, ContextListener listener) {
        HashMap<NodeType, ExpressionInterpreter> expressions = new HashMap<>();
        ExpressionInterpreterSupplierImpl expressionsSupplier = new ExpressionInterpreterSupplierImpl(expressions);
        AdditiveInterpreter additiveInterpreter = new AdditiveInterpreter(expressionsSupplier);
        expressions.put(NodeType.ADDITIVE_EXPRESSION, additiveInterpreter);
        expressions.put(NodeType.MULTIPLICATIVE_EXPRESSION, additiveInterpreter);
        expressions.put(NodeType.LITERAL, new LiteralInterpreter(expressionsSupplier));


        HashMap<NodeType, StatementInterpreter> interpreters = new HashMap<>();
        StatementInterpreterSupplierImpl statementInterpreterSupplier = new StatementInterpreterSupplierImpl(interpreters);
        interpreters.put(NodeType.SOURCE, new SourceInterpreter(statementInterpreterSupplier));
        interpreters.put(NodeType.VARIABLE_DECLARATION, new DeclarationInterpreter(expressionsSupplier));
        interpreters.put(NodeType.PRINT_DECLARATION, new PrintInterpreter(expressionsSupplier));
        interpreters.put(NodeType.ASSIGNATION, new AssignationInterpreter(expressionsSupplier));

        InterpreterImpl interpreter = new InterpreterImpl(treeNodeSupplier, statementInterpreterSupplier);
        interpreter.getContext().addListener(listener);
        return interpreter;
    }
}
