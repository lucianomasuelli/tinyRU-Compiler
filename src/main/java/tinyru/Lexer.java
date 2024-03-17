package tinyru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Lexer {
    private final FileScanner scanner;

    public Lexer(String filePath) throws IOException {
        scanner = new FileScanner(filePath, StandardCharsets.UTF_8);
    }
    public Token nextToken() throws IOException {
        Token token = null;
        int currChar = scanner.getCurrentChar();
        switch (currChar) {
            case ' ', '\t', '\n', '\r' -> {scanner.advance();}
            case '{'-> {token = new Token(TokenType.LBRACE, "{", scanner.getLine(), scanner.getColumn()); scanner.advance();}
            case '}'-> {token = new Token(TokenType.RBRACE, "}", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case '('-> {token = new Token(TokenType.LPAREN, "(", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case ')'-> {token = new Token(TokenType.RPAREN, ")", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case '['-> {token = new Token(TokenType.LBRACKET, "[", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case ']'-> {token = new Token(TokenType.RBRACKET, "]", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case '+'-> {
                AFRUnaryOp afrSum = new AFRUnaryOp();
                token = afrSum.recognize('+',scanner);}
            case '-'-> {
                AFRUnaryOp afrSub = new AFRUnaryOp();
                token = afrSub.recognize('-',scanner);
            }
            case '*'-> {token = new Token(TokenType.PROD, "*", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case '/'-> {
                if(scanner.seeNextChar() == '?'){ //En caso de ser un comentario
                    scanner.advance();
                    while (scanner.getCurrentChar() != '\n' && scanner.getCurrentChar() != -1){
                        scanner.advance();
                    }
                    scanner.advance();
                }
                else {
                    token = new Token(TokenType.DIV, "/", scanner.getLine(), scanner.getColumn());scanner.advance();
                }
            }
            case '='-> {
                AFRComparision afrComparision = new AFRComparision();
                token = afrComparision.recognize('=', scanner);
            }
            case ';'-> {token = new Token(TokenType.SEMICOLON, ";", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case ','-> {token = new Token(TokenType.COMMA, ",", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case ':'-> {token = new Token(TokenType.COLON, ":", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case '.'-> {token = new Token(TokenType.CONSTRUCT, ".", scanner.getLine(), scanner.getColumn());scanner.advance();}
            case '<'-> {
                AFRComparision afrComparision = new AFRComparision();
                token = afrComparision.recognize('<', scanner);
            }
            case '>'-> {
                AFRComparision afrComparision = new AFRComparision();
                token = afrComparision.recognize('>', scanner);
            }
            case '"' -> {}
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                AFRNumber afrNumber = new AFRNumber();
                token = afrNumber.recognize(scanner);
            }
            //caso en el que el caracter es una letra mayúscula
            case 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' -> {
                AFRStructID afrStructID = new AFRStructID();
                token = afrStructID.recognize(scanner);
            }
            // caso en el que el caracter es una letra minúscula
            case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' -> {
                AFRIdentifier afrIdentifier = new AFRIdentifier();
                token = afrIdentifier.recognize(scanner);
            }
            default -> {
                throw new IllegalCharError((char) scanner.getCurrentChar(), scanner.getLine(), scanner.getColumn());
            }
        }
        if(scanner.getCurrentChar() == '\uFFFF') {
            scanner.close();
        }
        return token;
    }

    public int getCurrentChar() {
        return scanner.getCurrentChar();
    }
}

