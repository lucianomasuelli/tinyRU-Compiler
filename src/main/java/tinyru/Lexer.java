package tinyru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public List<Token> tokens = new ArrayList<>();

    public void tokenize(String filePath) throws IOException {
        FileScanner scanner = new FileScanner(filePath, StandardCharsets.UTF_8);
        int currChar = scanner.getCurrentChar();
        while (currChar != -1) {
            switch (currChar) {
                case ' ', '\t', '\n', '\r' -> {break;}
                case '{'-> tokens.add(new Token(TokenType.LBRACE, "{", scanner.getLine(), scanner.getColumn()));
                case '}'-> tokens.add(new Token(TokenType.RBRACE, "}", scanner.getLine(), scanner.getColumn()));
                case '('-> tokens.add(new Token(TokenType.LPAREN, "(", scanner.getLine(), scanner.getColumn()));
                case ')'-> tokens.add(new Token(TokenType.RPAREN, ")", scanner.getLine(), scanner.getColumn()));
                case '+'-> tokens.add(new Token(TokenType.SUM, "+", scanner.getLine(), scanner.getColumn()));
                case '-'-> tokens.add(new Token(TokenType.RESTA, "-", scanner.getLine(), scanner.getColumn()));
                case '*'-> tokens.add(new Token(TokenType.PROD, "*", scanner.getLine(), scanner.getColumn()));
                case '/'-> tokens.add(new Token(TokenType.DIV, "/", scanner.getLine(), scanner.getColumn()));
                case '='-> tokens.add(new Token(TokenType.ASSIGN, "=", scanner.getLine(), scanner.getColumn()));
                case ';'-> tokens.add(new Token(TokenType.SEMICOLON, ";", scanner.getLine(), scanner.getColumn()));
                case ','-> tokens.add(new Token(TokenType.COMMA, ",", scanner.getLine(), scanner.getColumn()));
                case '"' -> {afrString(scanner);}
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {afrNumber(scanner);}
                //caso en el que el caracter es una letra mayúscula
                case 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' -> {afrIdentifier(scanner);}
                // caso en el que el caracter es una letra minúscula
                case 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' -> {afrIdentifier(scanner);}
            }
            scanner.advance();
            currChar = scanner.getCurrentChar();
        }
        scanner.close();
    }

    private void afrNumber(FileScanner scanner) throws IOException {
        StringBuilder number = new StringBuilder();
        int currChar = scanner.getCurrentChar();
        int nextChar = scanner.seeNextChar();
        while (Character.isDigit(nextChar)) {
            scanner.advance();
            currChar = scanner.getCurrentChar();
            number.append((char) currChar);
            nextChar = scanner.seeNextChar();
        }
        tokens.add(new Token(TokenType.NUM, number.toString(), scanner.getLine(), scanner.getColumn()));
    }

    private void afrString(FileScanner scanner) throws IOException {
        StringBuilder string = new StringBuilder();
        scanner.advance();
        int currChar = scanner.getCurrentChar();
        while (currChar != '"') {
            string.append((char) currChar);
            scanner.advance();
            currChar = scanner.getCurrentChar();
        }
        // agregar comillas dobles al principio y al final del string
        string.insert(0, "\"");
        string.append("\"");
        tokens.add(new Token(TokenType.STRING, string.toString(), scanner.getLine(), scanner.getColumn()));
    }

    private void afrIdentifier(FileScanner scanner) throws IOException {
        StringBuilder identifier = new StringBuilder();
        int currChar = scanner.getCurrentChar();

        while (Character.isLetterOrDigit(currChar)) {
            identifier.append((char) currChar);
            scanner.advance();
            currChar = scanner.getCurrentChar();
        }
        tokens.add(new Token(TokenType.ID, identifier.toString(), scanner.getLine(), scanner.getColumn()));
    }
}
