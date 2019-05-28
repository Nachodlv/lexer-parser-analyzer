package Lexer.states;

import Lexer.LexerAutomaton;

import java.util.Map;

public abstract class LexerStateImpl implements LexerState {
    private Map<Matcher, LexerState> adjacentStates;

    public LexerStateImpl(Map<Matcher, LexerState> adjacentStates) {
        this.adjacentStates = adjacentStates;
    }

    @Override
    public void match(char character, LexerAutomaton automaton) {
        adjacentStates.forEach((key, value) -> {
            if (key.match(character)) {
                automaton.setState(value);
            }
        });
    }

    @Override
    public boolean accepts(char character) {
        boolean found = false;
        for (Map.Entry<Matcher, LexerState> entry : adjacentStates.entrySet()) {
            if (entry.getKey().match(character)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
