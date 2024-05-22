package tinyru;

import tinyru.etapa1.Exceptions.LexerError;
import tinyru.etapa1.Lexer;
import tinyru.etapa2.Exceptions.ParserError;
import tinyru.etapa2.Parser;
import tinyru.etapa3.JSONGenerator;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.AST.AbstractSyntaxTree;

import java.io.IOException;

public class SemanticExecutor extends Executor {

    public SemanticExecutor(String outputFile) {
        super(outputFile);
    }

    @Override
    public void execute(String filePath) {
        Lexer lexer = null;
        try {
            lexer = new Lexer(filePath);
            Parser parser = new Parser(lexer);
            AbstractSyntaxTree ast = parser.analyze();
            SymbolTable table = parser.getSymbolTable();
            ast.check(table);
            //printTable(table);

            //obtain the filename without the path
            String[] parts = filePath.split("/");
            String fileName = parts[parts.length - 1];
            // remove the extension
            fileName = fileName.substring(0, fileName.length() - 3);

            JSONGenerator json = new JSONGenerator(fileName);
            json.jasonifyST(table);
            json.jasonifyAST(ast, table);

            System.out.println("CORRECTO: ANÁLISIS SEMÁNTICO - SENTENCIAS");

        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
