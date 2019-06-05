package main;

import lexer.Lexer;
import lexer.LexerImpl;
import lexer.TokenMatch;
import parser.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        Parser parser = ParserBuilder.buildParser(lexer);
        String text;
        try {
            text = readFile(args);
        } catch (IOException error) {
            System.out.println(error.getMessage());
            return;
        }

        List<TokenMatch> lex = lexer.lex(text);
        parser.parse();
        System.out.println(lex);
    }

    private static String readFile(String[] args) throws IOException {
        String filePath = "./src/test_files/text.txt";
        if(args.length > 0) filePath = args[0];
        return new String(Files.readAllBytes(Paths.get(filePath)));

    }
}
