package Lexer;

import java.util.List;

public interface Lexer {
    public List<Token> lex(Token token);
}
