package tinyru.AFR;

import tinyru.*;
import tinyru.Exceptions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AFRChar {
    //transition_1: acepta todos los caracteres conocidos quitando '\\', '\'', salto de linea.
    private final Set<Character> transition_1 = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm','n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M','N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '[',']','{','}', '<', '>', '=', '!', '+', '-','/',
            '*','%','&','|','^','~',',',';',':','.', ' ','¡','¿','?','@'));

    private final Set<Character> transition_2 = new HashSet<>(Arrays.asList('\\'));

    public Token recognize(FileScanner scanner) throws IOException {
        int initialLine = scanner.getLine();
        int initialColumn = scanner.getColumn();
        int currState = 0;
        scanner.advance();
        int currChar = scanner.getCurrentChar();
        StringBuilder str = new StringBuilder();
        Token token = null;

        while (currState != -1){

            switch (currState){
                case 0-> {
                    if(transition_1.contains((char) currChar)){
                        currState = 1;
                    }
                    else {
                        if(transition_2.contains((char) currChar)){
                            currState = 2;
                        }
                        else {
                            throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                        }
                    }
                }
                case 1 -> {
                    if((char) currChar == '\''){
                        currState = -1;
                    }
                    else {
                        if((char) currChar == '\uFFFF' || (char) currChar == '\n'){
                            throw new UnterminatedCharError(scanner.getLine(), scanner.getColumn(), str.toString());

                        }
                        else {
                            throw new CharTooLongError(scanner.getLine(), scanner.getColumn());
                        }
                    }
                }
                case 2-> {
                    if(transition_1.contains((char) currChar)){
                        if ((char) currChar == '0'){
                            throw new IllegalCharError('\0', scanner.getLine(), scanner.getColumn());
                        }
                        currState = 1;
                    }
                }
            }
            if(currState != -1) {
                str.append((char) currChar);
                scanner.advance();
                currChar = scanner.getCurrentChar();
            }
            else {
                switch (str.toString()) {
                    case "\\t" -> token = new Token(TokenType.CHAR, "\\t", initialLine, initialColumn);
                    case "\\n" -> token = new Token(TokenType.CHAR, "\\n", initialLine, initialColumn);
                    case "\\r" -> token = new Token(TokenType.CHAR, "\\r", initialLine, initialColumn);
                    case "\\v" -> token = new Token(TokenType.CHAR, "\\v", initialLine, initialColumn);
                    default -> token = new Token(TokenType.CHAR, str.toString(), initialLine, initialColumn);
                }
            }
        }
        scanner.advance();
        return token;
    }
}
