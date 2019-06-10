package lexer;

import lexer.exceptions.InvalidTokenException;

public class InvalidAutomaton extends LexerHandler{

    @Override
    TokenMatch handleString(String text) {
        throw new InvalidTokenException(text);
    }
}
