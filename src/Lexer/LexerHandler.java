package Lexer;

public abstract class LexerHandler {
    private LexerHandler handler;

    TokenMatch handleString(String text, int column, int row) {
        return handler.handleString(text, column, row);
    }
}
