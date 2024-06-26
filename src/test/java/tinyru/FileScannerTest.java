package tinyru;

import tinyru.etapa1.FileScanner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FileScannerTest {
    @org.junit.jupiter.api.Test
    void scanFile() {
        String filePath = "src/test/resources/test1.ru";
        try {
            FileScanner scanner = new FileScanner(filePath, StandardCharsets.UTF_8);

            StringBuilder contentBuilder = new StringBuilder();
            // Leer y procesar caracteres del archivo
            while (scanner.getCurrentChar() != -1) {
                // Procesar el caracter actual
                contentBuilder.append((char) scanner.getCurrentChar());

                System.out.println("Next char: " + (char) scanner.seeNextChar());

                // Avanzar al siguiente caracter
                scanner.advance();
            }
            String expectedContent = "HOlaaaa";
            assertEquals(expectedContent, contentBuilder.toString());

            // Cerrar el scanner al finalizar
            scanner.close();
        } catch (IOException e) {
            fail("Se produjo una excepción al leer el archivo: " + e.getMessage());
        }
    }
}