package lexer;

import lexer.exceptions.InvalidTokenException;
import main.LexerBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LexTest {

    @Test
    void invalidToken() {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        String text = "let a: string = \"a";

        assertThrows(RuntimeException.class, () -> lexer.lex(text));
    }

    @Test
    void variableStartsWithNumber() {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        String text = "let 4a: boolean = 3;";
        assertThrows(RuntimeException.class, () -> lexer.lex(text));
    }

    @Test
    void normalStatement() {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        String text = "let a:string=3;";
        List<TokenMatch> tokens = lexer.lex(text);
        Token[] types = {
                Token.LET, Token.WHITESPACE, Token.IDENTIFIER, Token.COLON, Token.STRING, Token.EQUALS,
                Token.NUMERIC_LITERAL, Token.SEMI_COLON};
        for (int i = 0; i < tokens.size(); i++) {
            assertEquals(types[i], tokens.get(i).getToken());
        }
    }
}
