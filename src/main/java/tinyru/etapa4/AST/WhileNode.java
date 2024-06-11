package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa4.Exceptions.TypesMismatchError;
import tinyru.etapa5.CodeGenerator;

import java.util.HashSet;
import java.util.Set;

import static tinyru.etapa1.TokenType.*;

public class WhileNode extends SentenciaNode {

    Token token;
    ExpresionNode condicional;
    SentenciaNode cuerpo;

    public WhileNode(Token token, ExpresionNode condicional, SentenciaNode cuerpo) {
        this.token = token;
        this.condicional = condicional;
        this.cuerpo = cuerpo;
    }

    @Override
    public void print() {
        System.out.print("while (");
        condicional.print();
        System.out.println(") {");
        cuerpo.print();
        System.out.println("}");
    }

    @Override
    public String check(SymbolTable st){
        String type = condicional.check(st);
        if (!type.equals("Bool")){
            throw new TypesMismatchError(type, "Bool", token.getLine(), token.getColumn());
        }
        cuerpo.check(st);
        return type;
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"WhileNode\": {\n";
        json += "\"condicional\": " + condicional.jsonify() + ",\n";
        json += "\"cuerpo\": " + cuerpo.jsonify() + "\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        cg.increaseWhileCounter();
        Integer whileCounter = cg.getWhileCounter();
        String whileLabel = "while_" + whileCounter;
        String endWhileLabel = "end_while_" + whileCounter;

        cg.getTextSection().append(whileLabel).append(":\n");

        condicional.generateCode(cg);

        //Si la condicion es falsa, salta al final del while
        cg.getTextSection().append("beq $a0, $zero, ").append(endWhileLabel).append("\n");

        cuerpo.generateCode(cg);

        cg.getTextSection().append("j ").append(whileLabel).append("\n");

        cg.getTextSection().append(endWhileLabel).append(":\n");
    }

}
