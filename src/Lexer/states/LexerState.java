package Lexer.states;


import Lexer.LexerAutomaton;

public interface LexerState {
    boolean isAccepting();
    void match(char character, LexerAutomaton automaton);
    boolean accepts(char character);
}
