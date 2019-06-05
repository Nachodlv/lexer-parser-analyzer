package parser;

import lexer.TokenMatch;
import parser.nodes.TerminalNode;
import parser.nodes.TreeNode;
import parser.states.ParserState;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ParseAutomaton {
    private TokenManager tokenManager;
    private ParserState currentState;

    public ParseAutomaton(TokenManager tokenManager, ParserState currentState) {
        this.tokenManager = tokenManager;
        this.currentState = currentState;
    }

    TreeNode handleTokens(List<TokenMatch> tokens) {
        Stack<TreeNode> tokenStack = new Stack<>();
        List<TreeNode> treeNodes = tokens.stream().map(token -> tokenManager.getTreeNode(token)).collect(Collectors.toList());
        treeNodes.add(new TerminalNode(NodeType.$));
        int index = 0;
        while (!currentState.isAccepting()) {
            TreeNode treeNode = treeNodes.get(index);
            currentState.match(tokenStack, treeNode, this);
            tokenStack.push(treeNode);
            index++;
        }
        tokenStack.pop();
        return tokenStack.peek();
    }

    public void setCurrentState(ParserState currentState) {
        this.currentState = currentState;
    }
}
