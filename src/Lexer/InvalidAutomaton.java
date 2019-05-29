package Lexer;

import Lexer.exceptions.InvalidTokenException;

public class InvalidAutomaton extends LexerHandler{

    @Override
    TokenMatch handleString(String text) {
        throw new InvalidTokenException();
    }
}
