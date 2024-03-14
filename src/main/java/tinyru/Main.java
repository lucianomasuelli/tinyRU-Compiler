package tinyru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            //System.out.println("Uso: java -jar etapa1.jar archivo.ru");

            TokenType tkType = TokenType.PSTRUCT;
            Token token = new Token(tkType, "struct", 1, 1);
            System.out.println(token.toString());
            return;
        }

        String filePath = args[0];
        try {
            FileScanner scanner = new FileScanner(filePath, StandardCharsets.UTF_8);

            // Leer y procesar caracteres del archivo
            while (scanner.getCurrentChar() != -1) {
                // Procesar el caracter actual
                System.out.print((char) scanner.getCurrentChar());

                System.out.println("Next char: " + (char) scanner.seeNextChar());

                // Avanzar al siguiente caracter
                scanner.advance();
            }

            // Cerrar el scanner al finalizar
            scanner.close();
        } catch (IOException e) {
            System.err.println("Se produjo una excepción al leer el archivo: " + e.getMessage());
        }
    }
}
