package Lexer;

public interface TokenMatch {
    int getColumn();
    int getRow();
    Token getToken();
    String getValue();
    void setColumn(int column);
    void setRow(int row);
}
