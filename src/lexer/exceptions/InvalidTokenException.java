package lexer.exceptions;

public class InvalidTokenException extends RuntimeException {

    private String invalidToken;

    public InvalidTokenException(String invalidToken) {
        this.invalidToken = invalidToken;
    }

    public String getInvalidToken() {
        return invalidToken;
    }
}
