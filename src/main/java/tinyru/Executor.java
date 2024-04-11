package tinyru;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

import tinyru.etapa1.Exceptions.LexerError;
import tinyru.etapa1.Lexer;
import tinyru.etapa1.Token;

/**
 * Clase que se encarga de ejecutar el analizador léxico
 * y escribir los resultados en un archivo de salida o
 * mostrarlos por consola
 * @author Luciano Masuelli
 */

public class Executor {
    private String outputFile;

    public Executor(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * Método que ejecuta el analizador léxico
     * En caso de que se haya especificado un archivo de salida
     * se escribe en el los resultados de la ejecución, caso contrario
     * se muestra la salida por consola
     * @param filePath ruta del archivo a leer
     * @throws RuntimeException
     * @throws LexerError cuando se encuentra un error léxico
     */
    public void execute(String filePath) {
        Lexer lexer = null;
        try {
            lexer = new Lexer(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            int i = 0;
            ArrayList<Token> tokens = new ArrayList<Token>();
            int currChar = lexer.getCurrentChar();
            Token token;
            while (currChar != -1) {
                token = lexer.nextToken();
                if (token != null) {
                    tokens.add(token);
                }
                currChar = lexer.getCurrentChar();
            }

            if (outputFile != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                    writer.write("""
                            CORRECTO: ANÁLISIS LÉXICO\s
                            | TOKEN | LEXEMA | NÚMERO DE LINEA  (NÚMERO DE COLUMNA) |
                            """);
                    writer.newLine();
                    for (Token t : tokens) {
                        writer.write(String.format("""
                                | %s | %s | LINEA %d (COLUMNA %d) |
                                %n""", t.getType(), t.getLexeme(), t.getLine(), t.getColumn()));
                    }
                }
            }
            else {
                System.out.println("""
                    CORRECTO: ANÁLISIS LÉXICO\s
                    | TOKEN | LEXEMA | NÚMERO DE LINEA  (NÚMERO DE COLUMNA) |
                    """);
                for (Token t : tokens) {
                    System.out.printf("""
                        | %s | %s | LINEA %d (COLUMNA %d) |
                        %n""", t.getType(), t.getLexeme(), t.getLine(), t.getColumn());
                }
            }

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (LexerError e) {
            System.out.println(e.getMessage());

        }
    }
}
