package tinyru.AFR;

import tinyru.FileScanner;
import tinyru.Exceptions.*;
import tinyru.Token;
import tinyru.TokenType;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que se encarga de reconocer los identificadores y palabras reservadas
 *
 * @autor Luciano Masuelli
 */
public class AFRIdentifier {
    //trsnsition 1 solo acpta letras minusculas
    private final Set<Character> transition_1 = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm','n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M','N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private final Set<Character> transition_2 = new HashSet<>(Arrays.asList(' ','<', '>', '+', '-', '*', '/', '=', ';', ',','.', '(', ')', '[',']','{','}','&','|' ,'\n', '\uFFFF','\\'));
    private final Set<String> reservedWords = new HashSet<>(Arrays.asList("if", "else", "while", "struct", "impl", "ret",
    "new", "fn", "st", "pri", "self", "true", "false", "nil", "start" ));


    public Token recognize(FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int currState = 0;
        int currChar = scanner.getCurrentChar();
        StringBuilder identifier = new StringBuilder();
        Token token = null;

        while (currState != -1) {
            // verifica si el caracter actual no es una letra minúscula
            if (!transition_1.contains((char) currChar)) {
                // verifica si el caracter actual es un espacio o un símbolo
                if (transition_2.contains((char) currChar)) {
                    currState = -1;
                }
                else {
                    throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                }
            }
            if(currState != -1) {
                identifier.append((char) currChar);
                scanner.advance();
                currChar = scanner.getCurrentChar();
            }
            else {
                if (reservedWords.contains(identifier.toString())) {
                    token = new Token(TokenType.valueOf("P" + identifier.toString().toUpperCase()), identifier.toString(), scanner.getLine(), initialColumn);
                }
            }
        }
        if (token == null) {
            token = new Token(TokenType.ID, identifier.toString(), scanner.getLine(), initialColumn);
        }
        return token;
    }
}

