package tinyru.etapa4;

import org.junit.jupiter.api.Test;
import tinyru.etapa1.Lexer;
import tinyru.etapa2.Parser;
import tinyru.etapa3.JSONGenerator;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.AST.AbstractSyntaxTree;

import java.io.IOException;

public class ASTTest {

    @Test
    void analyze() {
        try {
            AbstractSyntaxTree ast;
            JSONGenerator js = new JSONGenerator("src/test/resources/etapa4/test1.ru");
            Lexer lexer = new Lexer("src/test/resources/etapa4/test1.ru");
            Parser parser = new Parser(lexer);
            ast = parser.analyze();
            SymbolTable table = parser.getSymbolTable();
            //ast.print();
            //ast.check(table);
            System.out.println(js.jasonifyAST(ast,table));
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
