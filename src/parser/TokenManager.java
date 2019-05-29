package parser;

import lexer.Token;
import lexer.TokenMatch;
import parser.nodes.TerminalNode;
import parser.nodes.TreeNode;

import java.util.Map;

public class TokenManager {
    private Map<Token, NodeType> tokenNodeTypeMap;

    public TokenManager(Map<Token, NodeType> tokenNodeTypeMap) {
        this.tokenNodeTypeMap = tokenNodeTypeMap;
    }

    TreeNode getTreeNode(TokenMatch token) {
        NodeType nodeType = tokenNodeTypeMap.get(token.getToken());
        return new TerminalNode(nodeType, token.getColumn(), token.getRow(), token.getValue());
    }
}
