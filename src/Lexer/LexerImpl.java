package Lexer;

import java.util.List;

public class LexerImpl implements Lexer {

    LexerHandler handler;
    List<TokenMatch> tokens;

    @Override
    public List<Token> lex(Token token) {
        return null;
    }

    public List<TokenMatch> getTokens() {
        return tokens;
    }
}
