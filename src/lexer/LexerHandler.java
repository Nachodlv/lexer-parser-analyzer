package lexer;

public abstract class LexerHandler {
    private LexerHandler handler;

    TokenMatch handleString(String text) {
        return handler.handleString(text);
    }

    void setHandler(LexerHandler handler) {
        this.handler = handler;
    }
}
