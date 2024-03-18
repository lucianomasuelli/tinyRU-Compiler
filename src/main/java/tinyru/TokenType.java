package tinyru;

// Enumeración para tipos de token
public enum TokenType {
    //Palabras reservadas
    PSTRUCT, PIMPL, PIF, PELSE, PRET, PWHILE, PNEW, PFN,
    PST, PPRI, PSELF, PTRUE, PFALSE, PNIL, PSTART,

    //Tipos de datos
    PARRAY, PINT, PSTR, PBOOL, PCHAR,

    //Operadores
    SUM, RESTA, DIV, PROD, MOD, INC, DEC,
    AND, OR, IGUAL, NOT, MAYOR, MENOR,
    MAYORIGUAL, MENORIGUAL, DIF, ASSIGN,

    //Simbolos
    LPAREN, RPAREN, LBRACE, RBRACE,
    COMMA, SEMICOLON, COLON,   LBRACKET, RBRACKET, RETURN_TYPE,

    //Constructor
    CONSTRUCT,

    //Números
    NUM,

    //Cadenas
    STRING, ID, STRUCTID,

    //End of file
    EOF,
}

