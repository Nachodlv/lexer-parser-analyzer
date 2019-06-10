package main;

import lexer.TokenSupplier;
import parser.*;
import parser.rules.*;
import parser.states.*;

import java.util.HashMap;

public class ParserBuilder {
    private ParserBuilder() {
    }

    public static ParserImpl buildParser(TokenSupplier tokenSupplier) {

        FinalState finalState = new FinalState();

        RuleState initialState = new RuleState();

        // ASSIGNMENT
        ParserState assigmentState = buildAssigment(initialState);

        //DECLARATION
        ParserState declarationState = buildDeclaration(initialState);

        //PRINT
        ParserState printState = buildPrint(initialState);

        //INITIAL NODE
        HashMap<TreeNodeMatcher, Rule> rules = new HashMap<>();
        rules.put(new SimpleMatcher(NodeType.SEMI_COLON), new LineRule());
        rules.put(new DoubleMatcher(NodeType.LINE, NodeType.$), new SourceRule());
        initialState.setRules(rules);

        HashMap<NodeType, ParserState> adjacent = new HashMap<>();
        adjacent.put(NodeType.$, finalState);
        adjacent.put(NodeType.IDENTIFIER, assigmentState);
        adjacent.put(NodeType.LET, declarationState);
        adjacent.put(NodeType.PRINT, printState);
        initialState.setAdjacentNodes(adjacent);

        return new ParserImpl(tokenSupplier, new ParseAutomaton(new TokenManager(), initialState));
    }

    private static ParserState buildAssigment(ParserState initialState) {
        ParserState assigmentExpression = buildExpressionNodes(new AssignationRule(), initialState, NodeType.SEMI_COLON, true);
        NormalState assigmentState = new NormalState();
        HashMap<NodeType, ParserState> assignmentAdjacent = new HashMap<>();
        assignmentAdjacent.put(NodeType.EQUALS, assigmentExpression);
        assigmentState.setAdjacentNodes(assignmentAdjacent);
        return assigmentState;
    }

    private static ParserState buildPrint(ParserState initialState) {
        RuleState intermediatePrint = new RuleState();
        ParserState printExpression = buildExpressionNodes(new PrintRule(), intermediatePrint, NodeType.CLOSE_PARENTHESIS, false);

        HashMap<NodeType, ParserState> intermediateAdjacent = new HashMap<>();
        intermediateAdjacent.put(NodeType.SEMI_COLON, initialState);
        intermediatePrint.setAdjacentNodes(intermediateAdjacent);
        HashMap<TreeNodeMatcher, Rule> intermediateRule = new HashMap<>();
        intermediateRule.put(new SimpleMatcher(NodeType.CLOSE_PARENTHESIS), new PrintRule());
        intermediatePrint.setRules(intermediateRule);

        NormalState printState = new NormalState();
        HashMap<NodeType, ParserState> printAdjacent = new HashMap<>();
        printAdjacent.put(NodeType.OPEN_PARENTHESIS, printExpression);
        printState.setAdjacentNodes(printAdjacent);
        return printState;
    }

    private static ParserState buildDeclaration(ParserState initialState) {
        ParserState declarationExpression = buildExpressionNodes(new DeclarationRule(), initialState, NodeType.SEMI_COLON, true);
        NormalState letState = new NormalState();
        NormalState idState = new NormalState();
        NormalState colonState = new NormalState();
        RuleState declarationState = new RuleState();

        HashMap<NodeType, ParserState> letAdjacent = new HashMap<>();
        letAdjacent.put(NodeType.IDENTIFIER, idState);
        letState.setAdjacentNodes(letAdjacent);

        HashMap<NodeType, ParserState> idAdjacent = new HashMap<>();
        idAdjacent.put(NodeType.COLON, colonState);
        idState.setAdjacentNodes(idAdjacent);

        HashMap<NodeType, ParserState> colonAdjacent = new HashMap<>();
        colonAdjacent.put(NodeType.NUMBER, declarationState);
        colonAdjacent.put(NodeType.STRING, declarationState);
        colonState.setAdjacentNodes(colonAdjacent);

        HashMap<NodeType, ParserState> declarationAdjacent = new HashMap<>();
        declarationAdjacent.put(NodeType.SEMI_COLON, initialState);
        declarationAdjacent.put(NodeType.EQUALS, declarationExpression);
        declarationState.setAdjacentNodes(declarationAdjacent);

        HashMap<TreeNodeMatcher, Rule> declarationRules = new HashMap<>();
        declarationRules.put(new SimpleMatcher(NodeType.NUMBER), new TypeAnnotationRule());
        declarationRules.put(new SimpleMatcher(NodeType.STRING), new TypeAnnotationRule());
        declarationRules.put(new DoubleMatcher(NodeType.TYPE_ANNOTATION, NodeType.SEMI_COLON), new DeclarationRule());
        declarationState.setRules(declarationRules);

        return letState;
    }

    private static ParserState buildExpressionNodes(Rule rule, ParserState finalState, NodeType finalNodeType, boolean applyRule) {
        NormalState equalState = new NormalState();
        RuleState literalState = new RuleState();
        NormalState multState = new NormalState();
        NormalState plusState = new NormalState();

        HashMap<NodeType, ParserState> equalAdjacent = new HashMap<>();
        equalAdjacent.put(NodeType.NUMERIC_LITERAL, literalState);
        equalAdjacent.put(NodeType.STRING_LITERAL, literalState);
        equalAdjacent.put(NodeType.IDENTIFIER, literalState);
        equalState.setAdjacentNodes(equalAdjacent);

        HashMap<NodeType, ParserState> literalAdjacent = new HashMap<>();
        literalAdjacent.put(NodeType.INITIALIZER, finalState);
        literalAdjacent.put(NodeType.MULTIPLY, multState);
        literalAdjacent.put(NodeType.DIVIDE, multState);
        literalAdjacent.put(NodeType.PLUS, plusState);
        literalAdjacent.put(NodeType.MINUS, plusState);
        literalAdjacent.put(finalNodeType, finalState);

        literalState.setAdjacentNodes(literalAdjacent);

        HashMap<TreeNodeMatcher, Rule> literalRules = new HashMap<>();
        literalRules.put(new SimpleMatcher(NodeType.LITERAL), new MultiplicativeRule());

        Rule literalRule = new LiteralRule();
        literalRules.put(new SimpleMatcher(NodeType.NUMERIC_LITERAL), literalRule);
        literalRules.put(new SimpleMatcher(NodeType.STRING_LITERAL), literalRule);
        literalRules.put(new SimpleMatcher(NodeType.IDENTIFIER), literalRule);
        literalRules.put(new DoubleMatcher(NodeType.MULTIPLICATIVE_EXPRESSION, finalNodeType), new AdditiveRule());
        literalRules.put(new DoubleMatcher(NodeType.ADDITIVE_EXPRESSION, NodeType.SEMI_COLON), new InitializerRule());
        literalRules.put(new DoubleMatcher(NodeType.MULTIPLICATIVE_EXPRESSION, NodeType.PLUS), new AdditiveRule());
        literalRules.put(new DoubleMatcher(NodeType.MULTIPLICATIVE_EXPRESSION, NodeType.MINUS), new AdditiveRule());
        if (applyRule) literalRules.put(new SimpleMatcher(NodeType.INITIALIZER), rule);
        literalState.setRules(literalRules);

        multState.setAdjacentNodes(equalAdjacent);
        plusState.setAdjacentNodes(equalAdjacent);

        return equalState;
    }

}
