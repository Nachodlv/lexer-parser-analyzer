package main;

import lexer.Token;
import lexer.TokenSupplier;
import parser.*;
import parser.rules.*;
import parser.states.*;

import java.util.HashMap;

class ParserBuilder {
    private ParserBuilder(){}

    static Parser buildParser(TokenSupplier tokenSupplier) {

        FinalState finalState = new FinalState();
//        NormalState firstState = new NormalState();

        RuleState state = new RuleState();

        ParserState assigmentState = buildExpressionNodes(new AssignationRule(), state);
        ParserState declarationState = buildExpressionNodes(new DeclarationRule(), state);
        ParserState printState = buildExpressionNodes(new PrintRule(), state);


        HashMap<NodeType, ParserState> adjacent = new HashMap<>();
        adjacent.put(NodeType.$, finalState);
        adjacent.put(NodeType.IDENTIFIER, assigmentState);
        adjacent.put(NodeType.LET, declarationState);
        adjacent.put(NodeType.PRINT, printState);
        state.setAdjacentNodes(adjacent);

        HashMap<TreeNodeMatcher, Rule> rules = new HashMap<>();
        rules.put(new SimpleMatcher(NodeType.SEMI_COLON), new LineRule());
        rules.put(new DoubleMatcher(NodeType.LINE, NodeType.$), new SourceRule());
        state.setRules(rules);


        return new ParserImpl(tokenSupplier, new ParseAutomaton(buildTokenManager(), state));
    }

    private static ParserState buildExpressionNodes(Rule rule, ParserState finalState) {
        NormalState idState = new NormalState();
        NormalState equalState = new NormalState();
        RuleState literalState = new RuleState();
        NormalState multState = new NormalState();
        NormalState plusState = new NormalState();


        HashMap<NodeType, ParserState> idAdjacent = new HashMap<>();
        idAdjacent.put(NodeType.EQUALS, equalState);
        idState.setAdjacentNodes(idAdjacent);

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
        literalAdjacent.put(NodeType.SEMI_COLON, finalState);

        literalState.setAdjacentNodes(literalAdjacent);

        HashMap<TreeNodeMatcher, Rule> literalRules = new HashMap<>();
        literalRules.put(new SimpleMatcher(NodeType.LITERAL),new MultiplicativeRule());

        Rule literalRule = new LiteralRule();
        literalRules.put(new SimpleMatcher(NodeType.NUMERIC_LITERAL), literalRule);
        literalRules.put(new SimpleMatcher(NodeType.STRING_LITERAL), literalRule);
        literalRules.put(new SimpleMatcher(NodeType.IDENTIFIER), literalRule);
        literalRules.put(new DoubleMatcher(NodeType.MULTIPLICATIVE_EXPRESSION, NodeType.SEMI_COLON), new AdditiveRule());
        literalRules.put(new DoubleMatcher(NodeType.ADDITIVE_EXPRESSION, NodeType.SEMI_COLON), new InitializerRule());
        literalRules.put(new DoubleMatcher(NodeType.MULTIPLICATIVE_EXPRESSION, NodeType.PLUS), new AdditiveRule());
        literalRules.put(new DoubleMatcher(NodeType.MULTIPLICATIVE_EXPRESSION, NodeType.MINUS), new AdditiveRule());
        literalRules.put(new SimpleMatcher(NodeType.INITIALIZER), rule);
        literalState.setRules(literalRules);

        multState.setAdjacentNodes(equalAdjacent);
        plusState.setAdjacentNodes(equalAdjacent);

        return idState;
    }

    private static TokenManager buildTokenManager() {
        HashMap<Token, NodeType> tokens = new HashMap<>();

        tokens.put(Token.NUMERIC_LITERAL, NodeType.NUMERIC_LITERAL);
        tokens.put(Token.STRING_LITERAL, NodeType.STRING_LITERAL);
        tokens.put(Token.LET, NodeType.LET);
        tokens.put(Token.PRINT, NodeType.PRINT);
        tokens.put(Token.NUMBER, NodeType.NUMBER);
        tokens.put(Token.STRING, NodeType.STRING);
        tokens.put(Token.OPEN_PARENTHESIS, NodeType.OPEN_PARENTHESIS);
        tokens.put(Token.CLOSE_PARENTHESIS, NodeType.CLOSE_PARENTHESIS);
        tokens.put(Token.COLON, NodeType.COLON);
        tokens.put(Token.SEMI_COLON, NodeType.SEMI_COLON);
        tokens.put(Token.PLUS, NodeType.PLUS);
        tokens.put(Token.MINUS, NodeType.MINUS);
        tokens.put(Token.MULTIPLY, NodeType.MULTIPLY);
        tokens.put(Token.DIVIDE, NodeType.DIVIDE);
        tokens.put(Token.EQUALS, NodeType.EQUALS);
        tokens.put(Token.IDENTIFIER, NodeType.IDENTIFIER);

        return new TokenManager(tokens);
    }

}
