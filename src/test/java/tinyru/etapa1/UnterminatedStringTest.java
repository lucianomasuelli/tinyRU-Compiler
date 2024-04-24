package tinyru.etapa1;

import org.junit.jupiter.api.Test;
import tinyru.etapa1.Exceptions.UnterminatedStringError;

import static org.junit.jupiter.api.Assertions.*;

class UnterminatedStringTest {

    @Test
    void nextToken() {
        Lexer lexer;
        try {
            lexer = new Lexer("src/test/resources/unterminatedStringTest.ru");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            Token token = lexer.nextToken();
            System.out.println(token);
            fail("Se esperaba que se lanzara una excepción");
        } catch (UnterminatedStringError e) {
            System.out.println("Mensaje de error: \n" + e.getMessage());
        } catch (Exception e) {
            fail("Se esperaba que se lanzara una excepción UnterminatedCharError, pero se lanzó una excepción diferente: " + e.getClass().getSimpleName());
        }
    }
}