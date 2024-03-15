package tinyru;

import java.io.*;
import java.nio.charset.Charset;

public class FileScanner {
    private final BufferedReader reader;
    private int currentChar;
    private int line = 1;
    private int column;

    public FileScanner(String filePath, Charset charset) throws IOException {
        reader = new BufferedReader(new FileReader(filePath, charset));
        advance(); // Leer el primer caracter al inicializar el Scanner
    }

    public int getCurrentChar() {
        return currentChar;
    }

    public void advance() throws IOException {
        currentChar = reader.read(); // Leer el siguiente caracter del archivo
        if (currentChar == '\n') {
            line++;
            column = 0;
        }
        else {
            if (currentChar != ' ' && currentChar != '\t' && currentChar != '\r' && currentChar != -1) {
                column++;
            }
        }
    }

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
