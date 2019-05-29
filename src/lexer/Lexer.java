package lexer;

import java.util.List;

public interface Lexer {
    List<TokenMatch> lex(String text);
}
