package main;

import lexer.Lexer;
import lexer.TokenMatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = LexerBuilder.buildAutomatons();
        String text;
        try {
            text = readFile(args);
        } catch (IOException error) {
            System.out.println(error.getMessage());
            return;
        }

        List<TokenMatch> lex = lexer.lex(text);
        System.out.println(lex);
    }

    private static String readFile(String[] args) throws IOException {
        String filePath = "./src/test_files/text.txt";
        if(args.length > 0) filePath = args[0];
        return new String(Files.readAllBytes(Paths.get(filePath)));

    }
}
