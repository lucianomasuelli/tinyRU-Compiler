package tinyru.AFR;

import tinyru.FileScanner;
import tinyru.Exceptions.*;
import tinyru.Token;
import tinyru.TokenType;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class AFRUnaryOp {
    //transition_1: espacio, parentesis, numeros, letras y operadores + y -
    private final Set<Character> transition_1 = new HashSet<>(Arrays.asList(' ', '(', ')', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '-'));
    //transition_2: transition_1 - `+`
    private final Set<Character> transition_2 = new HashSet<>(Arrays.asList(' ', '(', ')', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-'));

    public Token recognize(char op, FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int currState = 0;
        int currChar = scanner.getCurrentChar();
        StringBuilder lexeme = new StringBuilder();
        Token token = null;

        while (currState != -1) {
            switch (currState) {
                case 0-> {
                    if (currChar == op) {
                        currState = 1;
                    }
                    else {
                        if (transition_2.contains((char) currChar)) {
                            currState = -1;
                        }
                        else {
                            throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                        }
                    }
                }
                case 1 -> {
                    if (op == '+'){
                        if (currChar == op) {
                            currState = 2;
                        }
                        else {
                            if (transition_1.contains((char) currChar)) {
                                currState = -1;
                            }
                            else {
                                throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                            }

                        }
                    }
                    else {
                        if (op == '-') {
                            if (currChar == op) {
                                currState = 2;
                            }
                            else {
                                if (currChar == '>') {
                                    lexeme.append((char) currChar);
                                    scanner.advance();
                                    currState = -1;
                                }
                                else {
                                    if (transition_1.contains((char) currChar)) {
                                        currState = -1;
                                    }
                                    else {
                                        throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                                    }
                                }
                            }
                        }
                    }

                }
                case 2->{
                    if (transition_1.contains((char) currChar)) {
                        currState = -1;
                    }
                    else {
                        throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                    }
                }
            }
            if(currState != -1) {
                lexeme.append((char) currChar);
                scanner.advance();
                currChar = scanner.getCurrentChar();
            }
        }
        switch (lexeme.toString()) {
            case "+" -> token = new Token(TokenType.SUM, "+", scanner.getLine(), initialColumn);
            case "-" -> token = new Token(TokenType.RESTA, "-", scanner.getLine(), initialColumn);
            case "++" -> token = new Token(TokenType.INC, "++", scanner.getLine(), initialColumn);
            case "--" -> token = new Token(TokenType.DEC, "--", scanner.getLine(), initialColumn);
            case "->" -> token = new Token(TokenType.RETURN_TYPE, "->", scanner.getLine(), initialColumn);
        }
        return token;
    }
}
