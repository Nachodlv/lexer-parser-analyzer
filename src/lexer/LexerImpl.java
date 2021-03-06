package lexer;

import lexer.exceptions.InvalidTokenException;

import java.util.ArrayList;
import java.util.List;

public class LexerImpl implements Lexer, TokenSupplier {

    private LexerHandler handler;
    private List<TokenMatch> tokens;

    public LexerImpl(LexerHandler handler) {
        this.handler = handler;
        this.tokens = new ArrayList<>();
    }

    @Override
    public List<TokenMatch> lex(String text) {
        tokens = lex(text.concat("\n"), new ArrayList<>(), 1, 1);
        return tokens;
    }

    private List<TokenMatch> lex(String text, List<TokenMatch> tokens, int row, int column) {
        if(text.length() == 1) return tokens;
        TokenMatch tokenMatch;
        try {
            tokenMatch = handler.handleString(text);
        } catch (InvalidTokenException e) {
            throw new RuntimeException("Invalid token at (" + row + ", " + column + ")");
        }
        int length = tokenMatch.getValue().length();
        tokenMatch.setRow(row);
        tokenMatch.setColumn(column);
        if(tokenMatch.getToken().equals(Token.NEW_LINE)) {
            column++;
            row = 0;
        }
        else row += length;
        tokens.add(tokenMatch);
        return lex(text.substring(length), tokens, row, column);
    }

    public List<TokenMatch> getTokens() {
        return tokens;
    }
}
