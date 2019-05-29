package lexer.states;

public class MatcherImpl implements Matcher {

    private String regex;

    public MatcherImpl(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean match(char character) {
        return String.valueOf(character).matches(regex);
    }
}
