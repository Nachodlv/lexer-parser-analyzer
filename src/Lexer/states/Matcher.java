package Lexer.states;

import Lexer.Lexer;

public interface Matcher {
    boolean match(char character);
}
