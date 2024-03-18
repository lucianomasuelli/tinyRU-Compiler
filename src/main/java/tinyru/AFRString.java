package tinyru;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AFRString {
    //transition_1: acepta todos los caracteres conocidos
    private final Set<Character> transition_1 = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm','n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M','N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '[',']','{','}', '<', '>', '=', '!', '+', '-','/',
            '*','%','&','|','^','~',',',';',':','.', ' ', '\n','¡','¿','?','@','\''));
    private final Set<Character> transition_2 = new HashSet<>(Arrays.asList('\\'));

    private final Set<Character> transition_3 = new HashSet<>(Arrays.asList('"'));

    private final Set<Character> transition_4 = new HashSet<>(Arrays.asList('\uFFFF'));


    public Token recognize(FileScanner scanner) throws IOException {
        int initialLine = scanner.getLine();
        int initialColumn = scanner.getColumn();
        int currState = 0;
        int length = 0;
        scanner.advance();
        int currChar = scanner.getCurrentChar();
        StringBuilder str = new StringBuilder();
        Token token = null;

        while (currState != -1){
            length += 1;
            switch (currState){

                case 0 -> {
                    if (transition_1.contains((char) currChar)){
                        currState = 0;
                    }
                    else {
                        if (transition_2.contains((char) currChar)){
                            currState = 1;
                        }
                        else {
                            if (transition_3.contains((char) currChar)) {
                                //Cierre de string
                                currState = -1;
                            }
                            else {
                                if (transition_4.contains((char) currChar)) {
                                    throw new UnterminatedStringError(scanner.getLine(), scanner.getColumn());
                                } else {
                                    throw new IllegalStringError((char) currChar, scanner.getLine(), scanner.getColumn());
                                }
                            }
                        }
                    }
                }
                case 1 -> {
                    if (transition_1.contains((char) currChar )
                            || transition_2.contains((char) currChar)
                            || transition_3.contains((char) currChar)){
                        // Reconocer que no sea un \0
                        if ((char) currChar == '0'){
                            throw new IllegalStringError('\0', initialLine, initialColumn);
                        }
                        currState = 0;
                    }
                    else {
                        if (transition_4.contains((char) currChar)){
                            throw new UnterminatedStringError(scanner.getLine(), scanner.getColumn());
                        }
                        else {
                            throw new IllegalStringError((char) currChar, scanner.getLine(), scanner.getColumn());
                        }
                    }
                }

            }
            if(currState != -1) {
                str.append((char) currChar);
                scanner.advance();
                currChar = scanner.getCurrentChar();
            }
        }
        if (length <= 1024){
            token = new Token(TokenType.STRING, str.toString(), initialLine, initialColumn);
            scanner.advance();
        } else{
            throw new StringTooLongError( initialLine, initialColumn);
        }

        return token;
    }
}
