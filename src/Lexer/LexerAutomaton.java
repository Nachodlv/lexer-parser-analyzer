package Lexer;

import Lexer.states.LexerState;

public class LexerAutomaton extends LexerHandler {
    private Token token;
    private LexerState initialState;
    private LexerState currentState;

    public LexerAutomaton(Token token, LexerState initialState) {
        this.token = token;
        this.initialState = initialState;
        this.currentState = initialState;
    }

    @Override
    TokenMatch handleString(String text, int column, int row) {
        char[] chars = text.toCharArray();
        currentState = initialState;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (!currentState.accepts(aChar)) break;

            currentState.match(aChar, this);
            if(currentState.isAccepting())
                return new TokenMatchImpl(column, row + i, token, getWord(chars, i - 1));
        }
        return super.handleString(text, column, row);
    }

    public void setState(LexerState newState) {
        currentState = newState;
    }

    private String getWord(char[] chars, int index) {
        return String.valueOf(chars).substring(0, index);
    }
}
