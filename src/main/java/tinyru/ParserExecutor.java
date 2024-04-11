package tinyru;

import tinyru.etapa1.Lexer;
import tinyru.etapa2.Parser;

public class ParserExecutor extends Executor{

        public ParserExecutor(String outputFile) {
            super(outputFile);
        }

        @Override
        public void execute(String filePath) {
            Lexer lexer = null;
            try {
                lexer = new Lexer(filePath);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Parser parser = new Parser(lexer);
            parser.analize();
        }
}
