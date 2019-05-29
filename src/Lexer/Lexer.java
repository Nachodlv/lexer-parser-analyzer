package Lexer;

import java.util.List;

public interface Lexer {
    public List<TokenMatch> lex(String text);
}
