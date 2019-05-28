package Lexer.states;

import java.util.Map;

public class AcceptingState extends LexerStateImpl {

    public AcceptingState(Map<Matcher, LexerState> adjacentStates) {
        super(adjacentStates);
    }

    @Override
    public boolean isAccepting() {
        return true;
    }
}
