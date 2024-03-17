package tinyru;

import tinyru.IllegalCharError;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AFRNumber {
    private final Set<Character> transition_1 = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private final Set<Character> transition_2 = new HashSet<>(Arrays.asList(' ', '+', '-', '*', '/', '=', ';', ',', '(', ')', '\n', '\uFFFF'));

    public Token recognize(FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int currState = 0;
        int currChar = scanner.getCurrentChar();
        StringBuilder number = new StringBuilder();

        while (currState != -1)  {
            if (!transition_1.contains((char) currChar)) {
                if (transition_2.contains((char) currChar)) {
                    currState = -1;
                }
                else {
                    throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                }
            }
            if(currState != -1) {
                number.append((char) currChar);
                scanner.advance();
                currChar = scanner.getCurrentChar();
            }
        }
        return new Token(TokenType.NUM, number.toString(), scanner.getLine(), initialColumn);
    }
}
