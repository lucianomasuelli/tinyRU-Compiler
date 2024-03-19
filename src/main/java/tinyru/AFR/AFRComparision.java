package tinyru.AFR;

import tinyru.FileScanner;
import tinyru.Exceptions.*;
import tinyru.Token;
import tinyru.TokenType;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AFRComparision {
    private final Set<Character> transition_1 = new HashSet<>(Arrays.asList(' ', '(', ')', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '-'));


    public Token recognize(char op, FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int currState = 0;
        int currChar = scanner.getCurrentChar();
        StringBuilder operator = new StringBuilder();
        Token token = null;

        while (currState != -1) {
            switch (currState) {
                case 0 -> {
                    if (currChar == op) {
                        currState = 1;
                    } else {
                        if (transition_1.contains((char) currChar)) {
                            currState = -1;
                        } else {
                            throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                        }
                    }
                }
                case 1 -> {
                    if (currChar == '=') {
                        currState = 2;
                    } else {
                        if (transition_1.contains((char) currChar)) {
                            currState = -1;
                        } else {
                            throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                        }

                    }
                }
                case 2 -> {
                    if (transition_1.contains((char) currChar)) {
                        currState = -1;
                    } else {
                        throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                    }
                }
            }
            if (currState != -1) {
                operator.append((char) currChar);
                scanner.advance();
                currChar = scanner.getCurrentChar();
            }
            switch (operator.toString()) {
                case "<" -> token = new Token(TokenType.MENOR, "<", scanner.getLine(), initialColumn);
                case "<=" -> token = new Token(TokenType.MENORIGUAL, "<=", scanner.getLine(), initialColumn);
                case ">" -> token = new Token(TokenType.MAYOR, ">", scanner.getLine(), initialColumn);
                case ">=" -> token = new Token(TokenType.MAYORIGUAL, ">=", scanner.getLine(), initialColumn);
                case "=" -> token = new Token(TokenType.ASSIGN, "=", scanner.getLine(), initialColumn);
                case "==" -> token = new Token(TokenType.IGUAL, "==", scanner.getLine(), initialColumn);
            }
        }
        return token;
    }
}
