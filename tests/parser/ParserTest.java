package parser;

import lexer.LexerImpl;
import main.LexerBuilder;
import main.ParserBuilder;
import org.junit.jupiter.api.Test;
import parser.exceptions.ParserException;
import parser.nodes.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void unhandledType() {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        ParserImpl parser = ParserBuilder.buildParser(lexer);
        String text = "let a:boolean = true;";
        lexer.lex(text);
        assertThrows(ParserException.class, parser::parse);
    }

    @Test
    void inputWithOutSemiColonShouldThrowError() {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        ParserImpl parser = ParserBuilder.buildParser(lexer);
        String text = "let a:string = 'a'";
        lexer.lex(text);
        assertThrows(ParserException.class, parser::parse);
    }
}
