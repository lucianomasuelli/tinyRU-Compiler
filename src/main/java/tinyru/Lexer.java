package tinyru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    String filePath;
    public List<Token> tokens = new ArrayList<>();

    public Lexer(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public List<Token> tokenize() throws IOException {
        FileScanner scanner = new FileScanner(filePath, StandardCharsets.UTF_8);
        int currChar = scanner.getCurrentChar();
        while (currChar != -1) {
            switch (currChar) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    break;
                case '{':
                    tokens.add(new Token(TokenType.LBRACE, "{", scanner.getLine(), scanner.getColumn()));
                    break;
                case '}':
                    tokens.add(new Token(TokenType.RBRACE, "}", scanner.getLine(), scanner.getColumn()));
                    break;
                case ';':
                    tokens.add(new Token(TokenType.SEMICOLON, ";", scanner.getLine(), scanner.getColumn()));
                    break;
                case ',':
                    tokens.add(new Token(TokenType.COMMA, ",", scanner.getLine(), scanner.getColumn()));
                    break;
                case '(':
                    tokens.add(new Token(TokenType.LPAREN, "(", scanner.getLine(), scanner.getColumn()));
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RPAREN, ")", scanner.getLine(), scanner.getColumn()));
                    break;
                case '=':
                    tokens.add(new Token(TokenType.ASSIGN, "=", scanner.getLine(), scanner.getColumn()));
                    break;
                case '+':
                    tokens.add(new Token(TokenType.SUM, "+", scanner.getLine(), scanner.getColumn()));
                    break;
                case '-':
                    tokens.add(new Token(TokenType.RESTA, "-", scanner.getLine(), scanner.getColumn()));
                    break;
                case '*':
                    tokens.add(new Token(TokenType.PROD, "*", scanner.getLine(), scanner.getColumn()));
                    break;
                case '/':
                    tokens.add(new Token(TokenType.DIV, "/", scanner.getLine(), scanner.getColumn()));
                    break;
                case '%':
                    tokens.add(new Token(TokenType.MOD, "%", scanner.getLine(), scanner.getColumn()));
                    break;
                case '&':
                    tokens.add(new Token(TokenType.AND, "&", scanner.getLine(), scanner.getColumn()));
                    break;
                case '|':
                    tokens.add(new Token(TokenType.OR, "|", scanner.getLine(), scanner.getColumn()));
                    break;
                case '!':
                    tokens.add(new Token(TokenType.NOT, "!", scanner.getLine(), scanner.getColumn()));
                    break;
                case '<':
                    tokens.add(new Token(TokenType.MENOR, "<", scanner.getLine(), scanner.getColumn()));
                    break;
                case '>':
                    tokens.add(new Token(TokenType.MAYOR, ">", scanner.getLine(), scanner.getColumn()));
                    break;
                case '.':
                    tokens.add(new Token(TokenType.DOT, ".", scanner.getLine(), scanner.getColumn()));
                    break;
                case '[':
                    tokens.add(new Token(TokenType.LBRACKET, "[", scanner.getLine(), scanner.getColumn()));
                    break;
                case ']':
                    tokens.add(new Token(TokenType.RBRACKET, "]", scanner.getLine(), scanner.getColumn()));
                    break;
                case '"':
                    //TODO: Implementar el manejo de strings


            }


            currChar = scanner.getCurrentChar();
            scanner.advance();
        }
        scanner.close();
        return tokens;
    }
    FileScanner scanner = new FileScanner(filePath, StandardCharsets.UTF_8);

}
