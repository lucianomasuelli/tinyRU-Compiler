package tinyru;

import java.io.*;
import java.nio.charset.Charset;

public class FileScanner {
    private BufferedReader reader;
    private int currentChar;

    public FileScanner(String filePath, Charset charset) throws IOException {
        reader = new BufferedReader(new FileReader(filePath, charset));
        advance(); // Leer el primer caracter al inicializar el Scanner
    }

    public int getCurrentChar() {
        return currentChar;
    }

    public void advance() throws IOException {
        currentChar = reader.read(); // Leer el siguiente caracter del archivo
    }

    public void close() throws IOException {
        reader.close(); // Cerrar el BufferedReader cuando hayamos terminado de leer el archivo
    }
}
