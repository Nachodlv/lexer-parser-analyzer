package Lexer;

public class TokenMatchImpl implements TokenMatch {
    private int column;
    private int row;
    private Token token;
    private String value;

    public TokenMatchImpl(int column, int row, Token token, String value) {
        this.column = column;
        this.row = row;
        this.token = token;
        this.value = value;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public String getValue() {
        return value;
    }
}
