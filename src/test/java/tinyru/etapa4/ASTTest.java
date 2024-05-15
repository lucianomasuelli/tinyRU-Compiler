package tinyru.etapa4;

import org.junit.jupiter.api.Test;
import tinyru.etapa1.Lexer;
import tinyru.etapa2.Parser;
import tinyru.etapa3.JSONGenerator;
import tinyru.etapa3.SymbolTable;

import java.io.IOException;

public class ASTTest {

    @Test
    void analyze() {
        try {
            Lexer lexer = new Lexer("src/test/resources/etapa3/test1.ru");
            Parser parser = new Parser(lexer);
            parser.analyze();
            //AST ast = parser.getAST();
            //printTable(table);
            //printAST(ast);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

//void printAST(AST ast) {
//        for (ASTNode node : ast.getRoot()) {
//            node.print();
//        }
//    }
//}
