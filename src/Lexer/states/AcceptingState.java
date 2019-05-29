package Lexer.states;

import java.util.Map;

public class AcceptingState extends LexerStateImpl {

    @Override
    public boolean isAccepting() {
        return true;
    }
}
