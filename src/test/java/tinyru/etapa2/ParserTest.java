package tinyru.etapa2;

import org.junit.jupiter.api.Test;
import tinyru.etapa1.Lexer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void analyze() {
        try {
            Lexer lexer = new Lexer("src/test/resources/etapa2/failing/constructorNotInImpl.ru");
            Parser parser = new Parser(lexer);
            parser.analyze();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}