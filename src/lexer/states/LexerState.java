package lexer.states;


import lexer.LexerAutomaton;

public interface LexerState {
    boolean isAccepting();
    void match(char character, LexerAutomaton automaton);
    boolean accepts(char character);
}
