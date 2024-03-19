package tinyru;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Clase que se encarga de leer un archivo de texto
 *
 * @author Luciano Massuelli
 */
public class FileScanner {
    private final BufferedReader reader;
    private int currentChar;
    private int line = 1;
    private int column;

    /**
     * Constructor de la clase FileScanner
     * @param filePath ruta del archivo a leer
     * @param charset codificación del archivo
     * @throws IOException
     */
    public FileScanner(String filePath, Charset charset) throws IOException {
        reader = new BufferedReader(new FileReader(filePath, charset));
        advance(); // Leer el primer caracter al inicializar el Scanner
    }

    public int getCurrentChar() {
        return currentChar;
    }

    /**
     * Método que avanza al siguiente caracter del archivo
     * @throws IOException
     */
    public void advance() throws IOException {
        currentChar = reader.read(); // Leer el siguiente caracter del archivo
        if (currentChar == '\n') {
            line++;
            column = 0;
        }
        else {
            if (currentChar != '\t' && currentChar != '\r' && currentChar != -1) {
                column++;
            }
        }
    }

    /**
     * Método que devuelve el siguiente caracter del archivo
     * @return int
     * @throws IOException
     */
    public int seeNextChar() throws IOException {
        reader.mark(1);
        currentChar = reader.read();
        reader.reset();
        return currentChar;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public void close() throws IOException {
        reader.close(); // Cerrar el BufferedReader cuando hayamos terminado de leer el archivo
    }
}
