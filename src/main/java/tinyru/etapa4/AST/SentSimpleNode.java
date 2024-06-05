package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

public class SentSimpleNode extends SentenciaNode {
    private ExpresionNode expresion;

    public SentSimpleNode(ExpresionNode expresion) {
        this.expresion = expresion;
    }


    @Override
    public String check(SymbolTable st) {
        return expresion.check(st);
    }

    @Override
    public String jsonify(){
        String json = "{\n";
        json += "\"SentSimpleNode\": {\n";
        json += "\"expresion\": ";
        json += expresion.jsonify();
        json += "\n";
        json += "}\n";
        json += "}\n";
        return json;
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        expresion.generateCode(cg);
    }

    public ExpresionNode getExpresion() {
        return expresion;
    }
}
