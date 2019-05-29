package lexer.states;

public class AcceptingState extends LexerStateImpl {

    @Override
    public boolean isAccepting() {
        return true;
    }
}
