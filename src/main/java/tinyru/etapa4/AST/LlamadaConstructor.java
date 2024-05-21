package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

import java.util.List;

public class LlamadaConstructor extends LlamadaConstructorNode{
    private List<ExpresionNode> args;
    private VarMetEncNode encadenado = null;

    public LlamadaConstructor(Token token, String struct, String metodo, List<ExpresionNode> args, VarMetEncNode encadenado){
        super(token, metodo, struct);
        this.args = args;
        this.encadenado = encadenado;
    }


    @Override
    public String check(String structType, SymbolTable st) {
        return "";  //TODO
    }

    @Override
    public String check(SymbolTable st) {
        return "";  //TODO
    }
}
