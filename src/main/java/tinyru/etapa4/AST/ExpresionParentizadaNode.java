package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

public class ExpresionParentizadaNode extends PrimarioNode{

    private ExpresionNode expresion;
    private VarMetEncNode encadenado;

    public ExpresionParentizadaNode(Token token, String struct, String metodo, ExpresionNode expresion, VarMetEncNode encadenado) {
        super(token,metodo,struct);
        this.expresion = expresion;
        this.encadenado = encadenado;
    }

    public void setEncadenado(VarMetEncNode encadenado) {
        this.encadenado = encadenado;
    }

    public void print() {
        System.out.print("(");
        expresion.print();
        System.out.print(")");
    }

    @Override
    public String check(String structType, SymbolTable st) {
        return expresion.check(st);
    }

    @Override
    public String check(SymbolTable st) {
        return expresion.check(st);
    }

}
