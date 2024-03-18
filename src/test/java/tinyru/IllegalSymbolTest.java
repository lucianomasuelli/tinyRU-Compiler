package tinyru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IllegalSymbolTest {

    @Test
    void nextToken() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/illegalSymbol.ru");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            Token token = lexer.nextToken();
            System.out.println(token);
            fail("Se esperaba que se lanzara una excepción");
        } catch (IllegalSymbolError e) {
            System.out.println("Mensaje de error: \n" + e.getMessage());
        } catch (Exception e) {
            fail("Se esperaba que se lanzara una excepción IllegalSymbolError, pero se lanzó una excepción diferente: " + e.getClass().getSimpleName());
        }
    }
}