package tinyru;

public class MainParser {
    public static void main(String[] args) {
        if (args.length == 1) {
            if (!args[0].endsWith(".ru")) {
                throw new IllegalArgumentException("El archivo a analizar debe tener extensión .ru");
            }
            String filePath = args[0];
            Executor executor = new ParserExecutor(null);
            executor.execute(filePath);
        }
        else {
            throw new IllegalArgumentException("La sintaxis de invocación debe ser: java -jar etapa1.jar <ARCHIVO_FUENTE>");
        }
    }
}
