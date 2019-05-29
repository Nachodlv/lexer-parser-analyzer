package Lexer;

public abstract class LexerHandler {
    private LexerHandler handler;

    TokenMatch handleString(String text) {
        return handler.handleString(text);
    }

    public void setHandler(LexerHandler handler) {
        this.handler = handler;
    }
}
