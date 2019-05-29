package Lexer;

public class TokenMatchImpl implements TokenMatch {
    private int column;
    private int row;
    private Token token;
    private String value;

    public TokenMatchImpl(Token token, String value) {
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

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }
}
