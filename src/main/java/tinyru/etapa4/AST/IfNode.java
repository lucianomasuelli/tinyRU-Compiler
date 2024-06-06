package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypesMismatchError;
import tinyru.etapa5.CodeGenerator;

import java.util.HashSet;
import java.util.Set;

import static tinyru.etapa1.TokenType.*;

public class IfNode extends SentenciaNode {
    Token token;
    ExpresionNode condicional;
    SentenciaNode cuerpo;
    SentenciaNode sentElse;

    public IfNode(Token token, ExpresionNode condicional, SentenciaNode cuerpo, SentenciaNode sino) {
        this.token = token;
        this.condicional = condicional;
        this.cuerpo = cuerpo;
        this.sentElse = sino;
    }

    public void print() {
        System.out.print("if (");
        condicional.print();
        System.out.println(") {");
        cuerpo.print();
        System.out.println("}");
        if (sentElse != null) {
            System.out.println("else {");
            sentElse.print();
            System.out.println("}");
        }
    }

    @Override
    public String check(SymbolTable st){
        String type = condicional.check(st);
        if (!type.equals("Bool")){
            throw new TypesMismatchError(token.getLexeme(), "Bool", token.getLine(), token.getColumn());
        }
        cuerpo.check(st);
        if (sentElse != null) {
            sentElse.check(st);
        }
        return type;
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"IfNode\": {\n";
        json += "\"condicional\": " + condicional.jsonify() + ",\n";
        json += "\"cuerpo\": " + cuerpo.jsonify() + ",\n";
        if (sentElse != null) {
            json += "\"sino\": " + sentElse.jsonify() + "\n";
        }
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        cg.increaseIfCounter();
        String ifLabel = "if_" + cg.getIfCounter();
        String elseLabel = "else_" + cg.getIfCounter();
        String endLabel = "end_if_" + cg.getIfCounter();

        // Generar código para la condición
        condicional.generateCode(cg);

        // Saltar a la etiqueta "else" si la condición es falsa
        cg.getTextSection().append("beqz $a0, ").append(elseLabel).append("\n");

        // Generar código para el cuerpo del if
        cuerpo.generateCode(cg);

        // Saltar a la etiqueta "endIf" después de ejecutar el cuerpo del if
        cg.getTextSection().append("j ").append(endLabel).append("\n");

        // Colocar la etiqueta "else" antes del cuerpo del else
        cg.getTextSection().append(elseLabel).append(":\n");

        // Generar código para el cuerpo del else, si existe
        if (sentElse != null) {
            sentElse.generateCode(cg);
        }

        // Colocar la etiqueta "endIf" después del cuerpo del else
        cg.getTextSection().append(endLabel).append(":\n");
    }
}
