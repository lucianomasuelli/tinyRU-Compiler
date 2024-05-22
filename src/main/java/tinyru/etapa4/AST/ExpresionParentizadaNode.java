package tinyru.etapa4.AST;

import tinyru.etapa3.SymbolTable;

public class ExpresionParentizadaNode extends SentenciaNode{

    private ExpresionNode expresion;

    public ExpresionParentizadaNode(ExpresionNode expresion) {
        this.expresion = expresion;
    }

    public void print() {
        System.out.print("(");
        expresion.print();
        System.out.print(")");
    }

    @Override
    public String check(SymbolTable st) {
        return expresion.check(st);
    }

    @Override
    public String jsonify() {
        return "{\n" +
                "\"tipo\": \"ExpresionParentizada\",\n" +
                "\"expresion\": " + expresion.jsonify() + "\n" +
                "}";
    }

}
