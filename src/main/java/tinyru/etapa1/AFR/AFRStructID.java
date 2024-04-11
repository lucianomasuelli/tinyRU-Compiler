package tinyru.etapa1.AFR;

import tinyru.Exceptions.*;
import tinyru.etapa1.Exceptions.IllegalCharError;
import tinyru.etapa1.Exceptions.IllegalStructIdError;
import tinyru.etapa1.FileScanner;
import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que reconocer los id de estructuras y los tipos de datos
 *
 * @autor Luciano Masuelli
 */
public class AFRStructID {
    //transicion 1: números y guion bajo
    private final Set<Character> transition_1 = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_'));
    //transiccion 2: letras mayusculas y minusculas
    private final Set<Character> transition_2 = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm','n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M','N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    //transicion 3: espacio, símbolos y fin de línea
    private final Set<Character> transition_3 = new HashSet<>(Arrays.asList(' ','<', '>', '+', '-', '*', '/', '=', ';', ',','.', '(', ')', '[',']', '{','}' ,'\n', '\uFFFF','\\'));

    private final Set<String> dataTypes = new HashSet<>(Arrays.asList("Array", "Int", "Str", "Bool", "Char"));


    public Token recognize(FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int currState = 0;
        int currChar = scanner.getCurrentChar();
        int prevColumn = scanner.getColumn();
        int prevLine = scanner.getLine();
        StringBuilder structID = new StringBuilder();
        Token token = null;

        while (currState != -1) {
            switch (currState) {
                case 0 -> {
                    // verifica si el caracter actual no es un número o un guion bajo
                    if (!transition_1.contains((char) currChar)) {
                        // verifica si el caracter actual es una letra mayúscula o minúscula
                        if (transition_2.contains((char) currChar)) {
                            currState = 1;
                        }
                        else {
                            // obtengo el caracter anterior al actual
                            char prevChar = structID.charAt(structID.length() - 1);
                            throw new IllegalStructIdError(prevLine, prevColumn, structID.toString());
                        }
                    }
                }
                case 1 -> {
                    // verifica si el caracter actual no es una letra mayúscula o minúscula
                    if (!transition_2.contains((char) currChar)) {
                        // verifica si el caracter actual es un espacio o un símbolo
                        if (transition_3.contains((char) currChar)) {
                            currState = -1;
                        }
                        else {
                            // verifica si el caracter actual es un número o un guion bajo
                            if (transition_1.contains((char) currChar)) {
                                currState = 0;
                            }
                            else {
                                throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
                            }
                        }
                    }
                }
            }
            if(currState != -1) {
                prevColumn = scanner.getColumn();
                prevLine = scanner.getLine();
                structID.append((char) currChar);
                scanner.advance();
                currChar = scanner.getCurrentChar();
            }
            else {
                if (dataTypes.contains(structID.toString())) {
                    token = new Token(TokenType.valueOf("P" + structID.toString().toUpperCase()), structID.toString(), scanner.getLine(), initialColumn);
                }
            }
        }
        if (token == null) {
            token = new Token(TokenType.STRUCTID, structID.toString(), scanner.getLine(), initialColumn);
        }
        return token;
    }

}
