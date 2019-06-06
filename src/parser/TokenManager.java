package parser;

import lexer.Token;
import lexer.TokenMatch;
import parser.nodes.TerminalNode;
import parser.nodes.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    private Map<Token, NodeType> tokenNodeTypeMap;

    public TokenManager() {
        tokenNodeTypeMap = new HashMap<>();

        tokenNodeTypeMap.put(Token.NUMERIC_LITERAL, NodeType.NUMERIC_LITERAL);
        tokenNodeTypeMap.put(Token.STRING_LITERAL, NodeType.STRING_LITERAL);
        tokenNodeTypeMap.put(Token.LET, NodeType.LET);
        tokenNodeTypeMap.put(Token.PRINT, NodeType.PRINT);
        tokenNodeTypeMap.put(Token.NUMBER, NodeType.NUMBER);
        tokenNodeTypeMap.put(Token.STRING, NodeType.STRING);
        tokenNodeTypeMap.put(Token.OPEN_PARENTHESIS, NodeType.OPEN_PARENTHESIS);
        tokenNodeTypeMap.put(Token.CLOSE_PARENTHESIS, NodeType.CLOSE_PARENTHESIS);
        tokenNodeTypeMap.put(Token.COLON, NodeType.COLON);
        tokenNodeTypeMap.put(Token.SEMI_COLON, NodeType.SEMI_COLON);
        tokenNodeTypeMap.put(Token.PLUS, NodeType.PLUS);
        tokenNodeTypeMap.put(Token.MINUS, NodeType.MINUS);
        tokenNodeTypeMap.put(Token.MULTIPLY, NodeType.MULTIPLY);
        tokenNodeTypeMap.put(Token.DIVIDE, NodeType.DIVIDE);
        tokenNodeTypeMap.put(Token.EQUALS, NodeType.EQUALS);
        tokenNodeTypeMap.put(Token.IDENTIFIER, NodeType.IDENTIFIER);
    }

    public TokenManager(Map<Token, NodeType> tokenNodeTypeMap) {
        this.tokenNodeTypeMap = tokenNodeTypeMap;
    }

    TreeNode getTreeNode(TokenMatch token) {
        NodeType nodeType = tokenNodeTypeMap.get(token.getToken());
        return new TerminalNode(nodeType, token.getColumn(), token.getRow(), token.getValue());
    }
}
