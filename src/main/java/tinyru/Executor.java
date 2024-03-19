package tinyru;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Clase que se encarga de ejecutar el analizador léxico
 *
 * @autor Luciano Massuelli
 */

public class Executor {
    private String outputFile;

    public Executor(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * Método que ejecuta el analizador léxico
     * @param filePath ruta del archivo a leer
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
            System.out.println("""
                    CORRECTO: ANÁLISIS LÉXICO\s
                    | TOKEN | LEXEMA | NÚMERO DE LINEA  (NÚMERO DE COLUMNA) |
                    """);
            for (Token t : tokens) {
                System.out.printf("""
                        | %s | %s | %d (%d) |
                        %n""", t.getType(), t.getLexeme(), t.getLine(), t.getColumn());
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
                                | %s | %s | %d (%d) |
                                %n""", t.getType(), t.getLexeme(), t.getLine(), t.getColumn()));
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
