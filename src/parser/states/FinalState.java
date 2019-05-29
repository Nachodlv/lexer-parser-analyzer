package parser.states;

public class FinalState extends  ParserStateImpl {
    @Override
    public boolean isAccepting() {
        return true;
    }
}
