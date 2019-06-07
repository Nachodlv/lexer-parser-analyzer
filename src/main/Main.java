package main;

import interpreter.ContextListener;
import interpreter.Interpreter;
import lexer.LexerImpl;
import lexer.TokenMatch;
import parser.ParserImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        ParserImpl parser = ParserBuilder.buildParser(lexer);
        Interpreter interpreter = InterpreterBuilder.buildInterpreter(parser, new Printer());
        String text;
        try {
            text = readFile(args);
        } catch (IOException error) {
            System.out.println(error.getMessage());
            return;
        }

        lexer.lex(text);
        parser.parse();
        interpreter.interpret();
    }

    private static String readFile(String[] args) throws IOException {
        String filePath = "./src/test_files/text.txt";
        if (args.length > 0) filePath = args[0];
        return new String(Files.readAllBytes(Paths.get(filePath)));

    }
}
