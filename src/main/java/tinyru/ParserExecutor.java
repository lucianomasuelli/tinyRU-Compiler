package tinyru;

import tinyru.etapa1.Lexer;
import tinyru.etapa2.Parser;

import java.io.IOException;

public class ParserExecutor extends Executor{

        public ParserExecutor(String outputFile) {
            super(outputFile);
        }

        @Override
        public void execute(String filePath) {
            Lexer lexer = null;
            try {
                lexer = new Lexer(filePath);
                Parser parser = new Parser(lexer);
                parser.analyze();

                System.out.println("CORRECTO: ANÁLISIS SINTÁCTICO");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
