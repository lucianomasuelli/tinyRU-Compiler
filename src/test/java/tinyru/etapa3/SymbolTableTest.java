package tinyru.etapa3;

import org.junit.jupiter.api.Test;
import tinyru.etapa1.Lexer;
import tinyru.etapa2.Parser;
import tinyru.etapa3.JSONGenerator;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class SymbolTableTest {

    @Test
    void analyze() {
        try {
            Lexer lexer = new Lexer("src/test/resources/etapa3/test1.ru");
            Parser parser = new Parser(lexer);
            parser.analyze();
            SymbolTable table = parser.getSymbolTable();
            //printTable(table);
            JSONGenerator json = new JSONGenerator("test1.ru");
            String jsonText = json.jasonifyST(table);
            System.out.println(jsonText);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}