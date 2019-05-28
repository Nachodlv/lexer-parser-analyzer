package Lexer.states;

import java.util.Map;

public class NormalState extends LexerStateImpl {

    public NormalState(Map<Matcher, LexerState> adjacentStates) {
        super(adjacentStates);
    }

    @Override
    public boolean isAccepting() {
        return false;
    }
}
