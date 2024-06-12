package tinyru.etapa4;

import org.junit.jupiter.api.Test;
import tinyru.etapa1.Lexer;
import tinyru.etapa2.Parser;
import tinyru.etapa3.JSONGenerator;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.AST.AbstractSyntaxTree;
import tinyru.etapa5.CodeGenerator;

import java.io.IOException;

public class ASTTest {

    @Test
    void analyze() {
        try {
            AbstractSyntaxTree ast;
            Lexer lexer = new Lexer("src/test/resources/etapa5/test.ru");
            Parser parser = new Parser(lexer);
            ast = parser.analyze();
            SymbolTable table = parser.getSymbolTable();
            //ast.print();
            ast.check(table);
            JSONGenerator jsonGenerator = new JSONGenerator("ast");
            //System.out.println(jsonGenerator.jasonifyAST(ast,table));
            CodeGenerator codeGenerator = new CodeGenerator(table);
            ast.generateCode(codeGenerator);
            codeGenerator.createASM("test");
            System.out.println(codeGenerator.getCode());

        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

//void printAST(AST ast) {
//        for (ASTNode node : ast.getRoot()) {
//            node.print();
//        }
//    }
//}
