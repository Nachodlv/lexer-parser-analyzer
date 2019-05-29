package Main;


import Lexer.Lexer;
import Lexer.LexerImpl;
import Lexer.LexerAutomaton;
import Lexer.InvalidAutomaton;
import Lexer.states.*;
import Lexer.Token;
import Lexer.LexerHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class LexerBuilder {
    private LexerBuilder() {
    }

    public static Lexer buildAutomatons() {
        InvalidAutomaton invalidAutomaton = new InvalidAutomaton();

        //IDENTIFIER
        LexerStateImpl identifierState = new NormalState();
        LexerStateImpl aceptanceIdentifier = new AcceptingState();
        HashMap<Matcher, LexerState> identifierStateTransitions = new HashMap<>();
        identifierStateTransitions.put(new MatcherImpl("[a-zA-Z_$][0-9a-zA-Z_$]*"), identifierState);
        identifierStateTransitions.put(new MatcherImpl("[^a-zA-Z_$][^0-9a-zA-Z_$]*"), aceptanceIdentifier);
        LexerAutomaton identifier = new LexerAutomaton(invalidAutomaton, Token.IDENTIFIER, identifierState);

        //OPERATORS
        LexerHandler plus = oneCharAutomatons("+", identifier, Token.PLUS);
        LexerHandler minus = oneCharAutomatons("-", plus, Token.MINUS);
        LexerHandler multiply = oneCharAutomatons("*", minus, Token.MULTIPLY);
        LexerHandler divide = oneCharAutomatons("/", multiply, Token.DIVIDE);
        LexerHandler equals = oneCharAutomatons("=", divide, Token.EQUALS);

        //SEPARATORS
        LexerHandler openParenthesis = oneCharAutomatons("(", equals, Token.OPEN_PARENTHESIS);
        LexerHandler closeParenthesis = oneCharAutomatons(")", openParenthesis, Token.CLOSE_PARENTHESIS);
        LexerHandler colon = oneCharAutomatons(":", closeParenthesis, Token.COLON);
        LexerHandler semiColon = oneCharAutomatons(";", colon, Token.SEMI_COLON);

        //TYPES
        LexerHandler number = multipleCharsAutomatons("number", semiColon, Token.NUMBER);
        LexerHandler string = multipleCharsAutomatons("string", number, Token.STRING);

        //RESERVED WORDS
        LexerHandler let = multipleCharsAutomatons("let", string, Token.LET);
        LexerHandler print = multipleCharsAutomatons("print", let, Token.PRINT);

        //LITERALS
//        LexerHandler numLiteral = multipleCharsAutomatons("print", let, Token.PRINT);
//        LexerHandler stringLiteral = multipleCharsAutomatons("print", let, Token.PRINT);


        return new LexerImpl(null);
    }

    private static LexerHandler oneCharAutomatons(String character, LexerHandler handler, Token token) {
        LexerStateImpl firstState = new NormalState();
        LexerStateImpl secondState = new NormalState();
        LexerStateImpl acceptanceSum = new NormalState();
        HashMap<Matcher, LexerState> sumStates = new HashMap<>();
        sumStates.put(new MatcherImpl(character), secondState);
        firstState.setAdjacentStates(sumStates);
        HashMap<Matcher, LexerState> secondTransitions = new HashMap<>();
        secondTransitions.put(new MatcherImpl("^" + character), acceptanceSum);
        secondState.setAdjacentStates(secondTransitions);

        return new LexerAutomaton(handler, token, firstState);
    }

    private static LexerHandler multipleCharsAutomatons(String word, LexerHandler handler, Token token) {
        LexerStateImpl lastState = new AcceptingState();
        char[] chars = word.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            char aChar = chars[i];
            HashMap<Matcher, LexerState> transitions = new HashMap<>();
            transitions.put(new MatcherImpl(String.valueOf(aChar)), lastState);
            LexerStateImpl lexerState = new NormalState();
            lexerState.setAdjacentStates(transitions);
            lastState = lexerState;
        }
        return new LexerAutomaton(handler, token, lastState);
    }
}
