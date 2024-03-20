package tinyru.Exceptions;
/**
 * Clase que representa un error en el analizador léxico
 *
 * @autor Luciano Massuelli, Gabriel Mangione
 */
public class LexerError extends RuntimeException {
    private final String errorMessage;

    public LexerError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return "ERROR: LEXICO \n" + errorMessage;
    }
}














