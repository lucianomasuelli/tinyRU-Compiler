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
            Lexer lexer = new Lexer("src/test/resources/etapa3/failing/variableAlreadyDeclared.ru");
            Parser parser = new Parser(lexer);
            parser.analyze();
            SymbolTable table = parser.getSymbolTable();
            //printTable(table);
            JSONGenerator json = new JSONGenerator("test1.ru");
            String jsonText = json.jasonify(table);
            System.out.println(jsonText);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    void printTable(SymbolTable table) {
        for (String key : table.getStructTable().keySet()) {
            System.out.println("Struct: " + key);
            StructInput struct = table.getStructTable().get(key);

            System.out.println("    Parent: " + struct.getParent());

            for (String key2 : struct.getAttributeTable().keySet()) {
                System.out.println("    Attribute: " + key2);
            }
            for (String key2 : struct.getConstantTable().keySet()) {
                System.out.println("    Constant: " + key2);
            }
            for (String key2 : struct.getMethodTable().keySet()) {
                System.out.println("    Method: " + key2);
                MethodInput method = struct.getMethodTable().get(key2);
                System.out.println("        Return type: " + method.getReturnType());
                System.out.println("        Parameters:");
                for (String key3 : method.getParameterTable().keySet()) {
                    System.out.println("            "+ method.getParameterTable().get(key3).getType() + " " + key3);
                }

            }
        }
    }
}