package Lexer;

import java.util.List;

public interface TokenSupplier {
    List<TokenMatch> getTokens();
}
