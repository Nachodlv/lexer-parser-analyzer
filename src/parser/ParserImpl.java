package parser;

import lexer.Token;
import lexer.TokenSupplier;
import parser.nodes.TreeNode;

import java.util.stream.Collectors;

public class ParserImpl implements Parser, TreeNodeSupplier {
    private TreeNode treeNode;
    private TokenSupplier tokenSupplier;
    private ParseAutomaton parseAutomaton;

    public ParserImpl(TokenSupplier tokenSupplier, ParseAutomaton parseAutomaton) {
        this.tokenSupplier = tokenSupplier;
        this.parseAutomaton = parseAutomaton;
    }

    @Override
    public TreeNode parse() {
        treeNode = parseAutomaton.handleTokens(
                tokenSupplier
                        .getTokens()
                        .stream()
                        .filter(token -> token.getToken() != Token.NEW_LINE && token.getToken() != Token.WHITESPACE)
                        .collect(Collectors.toList()));
        return treeNode;
    }

    @Override
    public TreeNode getTree() {
        return treeNode;
    }
}
