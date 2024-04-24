package tinyru.etapa1;

import org.junit.jupiter.api.Test;
import tinyru.etapa1.Exceptions.CharTooLongError;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CharTooLongTest {

    @Test
    void nextToken() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/charTooLong.ru");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            Token token = lexer.nextToken();
            System.out.println(token);
            fail("Se esperaba que se lanzara una excepción");

        } catch (CharTooLongError e) {
            System.out.println("Mensaje de error: \n" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}