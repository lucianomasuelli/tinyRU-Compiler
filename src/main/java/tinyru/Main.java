package tinyru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Se necesita un argumento: la ruta del archivo a analizar");
        }

        String filePath = args[0];
        Executor executor = new Executor();
        executor.execute(filePath);
    }
}
