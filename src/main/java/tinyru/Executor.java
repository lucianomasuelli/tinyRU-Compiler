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
    protected final String outputFile;

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
    }
}
