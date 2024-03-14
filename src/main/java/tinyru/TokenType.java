package tinyru;

// Enumeración para tipos de token
public enum TokenType {
    //Palabras reservadas
    PSTRUCT, PIMPL, PIF, PELSE, PRET, PWHILE, PNEW, PFN,
    PST, PPRI, PSELF, PTRUE, PFALSE, PNIL,

    //Tipos de datos
    PARRAY, PINT, PSTR, PBOOL, PCHAR,

    //Operadores
    SUM, RESTA, DIV, PROD, MOD, INC, DEC,
    AND, OR, IGUAL, NOT, MAYOR, MENOR,
    MAYORIGUAL, MENORIGUAL, DIF, ASSIGN,

    //Simbolos
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    DOT, COMMA, SEMICOLON, COLON
}

