package interpreter;

import lexer.LexerImpl;
import main.InterpreterBuilder;
import main.LexerBuilder;
import main.ParserBuilder;
import org.junit.jupiter.api.Test;
import parser.ParserImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    private InterpreterImpl getInterpreter(String text) {
        LexerImpl lexer = LexerBuilder.buildAutomatons();
        ParserImpl parser = ParserBuilder.buildParser(lexer);
        InterpreterImpl interpreter = InterpreterBuilder.buildInterpreter(parser);
        lexer.lex(text);
        parser.parse();
        return interpreter;
    }

    @Test
    void undeclaredVariableShouldThrowException() {
        Interpreter interpreter = getInterpreter("a = 'hello world';");
        assertThrows(RuntimeException.class, interpreter::interpret);
    }

    @Test
    void incorrectTypeAssignmentShouldThrowException() {
        Interpreter interpreter = getInterpreter("let a:number = 'a';");
        assertThrows(RuntimeException.class, interpreter::interpret);
    }

    @Test
    void divideANumberByAStringShouldThrowException() {
        Interpreter interpreter = getInterpreter("print(1/'a');");
        assertThrows(RuntimeException.class, interpreter::interpret);
    }

    @Test
    void printingANumberShouldAppearInContext() {
        InterpreterImpl interpreter = getInterpreter("let a:number = 3 + 3; print(a * 2);");
        interpreter.interpret();
        Context context = interpreter.getContext();
        List<String> output = context.getOutput();
        assertEquals(1, output.size());
        assertEquals("12.0", output.get(0));
    }

    @Test
    void theSumOfTwoNumbersShouldResultInTheirSum() {
        InterpreterImpl interpreter = getInterpreter("let a:number=3;let b:number =5; print(a+b);");
        interpreter.interpret();
        List<String> output = interpreter.getContext().getOutput();
        assertEquals(1, output.size());
        assertEquals("8.0", output.get(0));
    }

    @Test
    void theSumOfANumberAndAStringShouldResultInAString() {
        InterpreterImpl interpreter = getInterpreter("let a:number=3;let b:string='hola';print(a+b);");
        interpreter.interpret();
        List<String> output = interpreter.getContext().getOutput();
        assertEquals(1, output.size());
        assertEquals("3.0hola", output.get(0));
    }


}
