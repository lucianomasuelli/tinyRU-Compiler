package tinyru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            if (!args[0].endsWith(".ru")) {
                throw new IllegalArgumentException("El archivo a analizar debe tener extensión .ru");
            }
            String filePath = args[0];
            if (args.length == 1) {
                Executor executor = new Executor(null);
                executor.execute(filePath);
            }
            else {
                if (args.length == 2) {
                    Executor executor = new Executor(args[1]);
                    executor.execute(filePath);
                }
                else {
                    throw new IllegalArgumentException("La sintaxis de invocación debe ser: java -jar etapa1.jar <ARCHIVO_FUENTE> [<ARCHIVO_SALIDA>]");
                }
            }
        }
            else {
                throw new IllegalArgumentException("La sintaxis de invocación debe ser: java -jar etapa1.jar <ARCHIVO_FUENTE> [<ARCHIVO_SALIDA>]");
            }
        }
    }
