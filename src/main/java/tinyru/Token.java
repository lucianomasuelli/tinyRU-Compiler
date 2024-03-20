package tinyru;

/**
 * Token class
 * Esta clase representa los token en el programa
 * Un token contiene un tipo, un lexema, una línea y una columna.
 * El tipo de token es un TokenType, el lexema es una cadena de caracteres,
 * la línea y columna son enteros que representan la ubicacion del token.
 *
 * @author Gabriel Mangione
 */
public class Token {

    private final TokenType type;
    private final String lexeme;

    private final int line;

    private final int column;

    public Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenType getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Token {" +
                "type=" + type +
                ", lexeme='" + lexeme +
                "', line=" + line +
                ", column=" + column +'}';
    }

}

