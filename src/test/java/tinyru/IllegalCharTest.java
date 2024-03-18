package tinyru;

import org.junit.jupiter.api.Test;
import tinyru.Exceptions.IllegalCharError;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IllegalCharTest {

    @Test
    void nextToken() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/illegalChar.ru");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Token token = lexer.nextToken();
            System.out.println(token);
            fail("Se esperaba que se lanzara una excepción");
        } catch (IllegalCharError e) {
            System.out.println("Mensaje de error: \n" + e.getMessage());
        } catch (Exception e) {
            fail("Se esperaba que se lanzara una excepción IllegalCharError, pero se lanzó una excepción diferente: " + e.getClass().getSimpleName());
        }
    }
}