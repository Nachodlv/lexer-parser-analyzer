package main;


import lexer.Lexer;
import lexer.LexerImpl;
import lexer.LexerAutomaton;
import lexer.InvalidAutomaton;
import lexer.states.*;
import lexer.Token;
import lexer.LexerHandler;

import java.util.HashMap;

public class LexerBuilder {
    private LexerBuilder() {
    }

    public static LexerImpl buildAutomatons() {
        InvalidAutomaton invalidAutomaton = new InvalidAutomaton();

        //IDENTIFIER
        LexerHandler identifier = getIdentifierAutomaton(invalidAutomaton);

        //OPERATORS
        LexerHandler plus = oneCharAutomatons("[+]", identifier, Token.PLUS);
        LexerHandler minus = oneCharAutomatons("[-]", plus, Token.MINUS);
        LexerHandler multiply = oneCharAutomatons("[*]", minus, Token.MULTIPLY);
        LexerHandler divide = oneCharAutomatons("[/]", multiply, Token.DIVIDE);
        LexerHandler equals = oneCharAutomatons("[=]", divide, Token.EQUALS);

        //SEPARATORS
        LexerHandler openParenthesis = oneCharAutomatons("[(]", equals, Token.OPEN_PARENTHESIS);
        LexerHandler closeParenthesis = oneCharAutomatons("[)]", openParenthesis, Token.CLOSE_PARENTHESIS);
        LexerHandler colon = oneCharAutomatons("[:]", closeParenthesis, Token.COLON);
        LexerHandler semiColon = oneCharAutomatons("[;]", colon, Token.SEMI_COLON);
        LexerHandler whiteSpace = oneCharAutomatons("[\\p{javaSpaceChar}]", semiColon, Token.WHITESPACE);
        LexerHandler newLine = oneCharAutomatons("[\n]", whiteSpace, Token.NEW_LINE);

        //TYPES
        LexerHandler number = multipleCharsAutomatons("number", newLine, Token.NUMBER);
        LexerHandler string = multipleCharsAutomatons("string", number, Token.STRING);

        //RESERVED WORDS
        LexerHandler let = multipleCharsAutomatons("let", string, Token.LET);
        LexerHandler print = multipleCharsAutomatons("print", let, Token.PRINT);

        //LITERALS
        LexerHandler numberLiteral = getNumberLiteralAutomaton(print);
        LexerHandler stringLiteralAutomaton = getStringLiteralAutomaton(numberLiteral);


        return new LexerImpl(stringLiteralAutomaton);
    }

    private static LexerHandler oneCharAutomatons(String character, LexerHandler handler, Token token) {
        LexerStateImpl firstState = new NormalState();
        LexerStateImpl secondState = new NormalState();
        LexerStateImpl acceptanceSum = new AcceptingState();
        HashMap<Matcher, LexerState> sumStates = new HashMap<>();
        sumStates.put(new MatcherImpl(character), secondState);
        firstState.setAdjacentStates(sumStates);
        HashMap<Matcher, LexerState> secondTransitions = new HashMap<>();
        secondTransitions.put(new MatcherImpl("(?s)."), acceptanceSum);
        secondState.setAdjacentStates(secondTransitions);

        return new LexerAutomaton(handler, token, firstState);
    }

    private static LexerHandler multipleCharsAutomatons(String word, LexerHandler handler, Token token) {

        LexerStateImpl acceptingState = new AcceptingState();
        LexerStateImpl lastState = new NormalState();
        HashMap<Matcher, LexerState> lastTransition = new HashMap<>();
        lastTransition.put(new MatcherImpl("[^a-zA-Z0-9_$]"), acceptingState);
        lastState.setAdjacentStates(lastTransition);

        char[] chars = word.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            char aChar = chars[i];
            HashMap<Matcher, LexerState> transitions = new HashMap<>();
            transitions.put(new MatcherImpl(String.valueOf(aChar)), lastState);
            LexerStateImpl lexerState = new NormalState();
            lexerState.setAdjacentStates(transitions);
            lastState = lexerState;
        }
        return new LexerAutomaton(handler, token, lastState);
    }

    private static LexerHandler getIdentifierAutomaton(LexerHandler lexerHandler) {
        LexerStateImpl identifierState = new NormalState();
        LexerStateImpl secondState = new NormalState();
        LexerStateImpl aceptanceIdentifier = new AcceptingState();
        HashMap<Matcher, LexerState> identifierStateTransitions = new HashMap<>();
        HashMap<Matcher, LexerState> secondStateTransitions = new HashMap<>();
        identifierStateTransitions.put(new MatcherImpl("[a-zA-Z_$]"), secondState);
        secondStateTransitions.put(new MatcherImpl("[0-9a-zA-Z_$]"), secondState);
        secondStateTransitions.put(new MatcherImpl("[^0-9a-zA-Z_$]"), aceptanceIdentifier);
        identifierState.setAdjacentStates(identifierStateTransitions);
        secondState.setAdjacentStates(secondStateTransitions);
        return new LexerAutomaton(lexerHandler, Token.IDENTIFIER, identifierState);
    }

    private static LexerHandler getNumberLiteralAutomaton(LexerHandler lexerHandler) {

        LexerStateImpl acceptingState = new AcceptingState();
        LexerStateImpl doubleState = new NormalState();
        LexerStateImpl intState = new NormalState();
        NormalState firstState = new NormalState();
        MatcherImpl numberMatcher = new MatcherImpl("[0-9]");
        MatcherImpl notNumberMatcher = new MatcherImpl("[^0-9a-zA-Z]");

        HashMap<Matcher, LexerState> intTransitions = new HashMap<>();
        intTransitions.put(new MatcherImpl("\\."), doubleState);
        intTransitions.put(notNumberMatcher, acceptingState);
        intTransitions.put(numberMatcher, intState);
        intState.setAdjacentStates(intTransitions);

        HashMap<Matcher, LexerState> doubleTransitions = new HashMap<>();
        doubleTransitions.put(numberMatcher, doubleState);
        doubleTransitions.put(notNumberMatcher, acceptingState);
        doubleState.setAdjacentStates(doubleTransitions);

        HashMap<Matcher, LexerState> firstTransitions = new HashMap<>();
        firstTransitions.put(numberMatcher, intState);
        firstState.setAdjacentStates(firstTransitions);

        return new LexerAutomaton(lexerHandler, Token.NUMERIC_LITERAL, firstState);
    }

    private static LexerHandler getStringLiteralAutomaton(LexerHandler lexerHandler) {
        LexerStateImpl acceptingState = new AcceptingState();
        NormalState finalState = new NormalState();
        NormalState firstState = new NormalState();

        HashMap<Matcher, LexerState> finalTransitions = new HashMap<>();
        finalTransitions.put(new MatcherImpl("(?s)."), acceptingState);
        finalState.setAdjacentStates(finalTransitions);

        LexerState simpleState = createStateForString("[']", finalState);

        LexerState doubleState = createStateForString("[\"]", finalState);


        HashMap<Matcher, LexerState> firstTransitions = new HashMap<>();
        firstTransitions.put(new MatcherImpl("\""), doubleState);
        firstTransitions.put(new MatcherImpl("'"), simpleState);
        firstState.setAdjacentStates(firstTransitions);

        return new LexerAutomaton(lexerHandler, Token.STRING_LITERAL, firstState);
    }

    private static LexerState createStateForString(String regex, LexerState nextState) {
        LexerStateImpl state = new NormalState();
        HashMap<Matcher, LexerState> transitions = new HashMap<>();
        transitions.put(new MatcherImpl("[" + regex.replace("[", "^")), state);
        transitions.put(new MatcherImpl(regex), nextState);
        state.setAdjacentStates(transitions);
        return state;
    }
}
