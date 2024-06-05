package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;
import tinyru.etapa5.CodeGenerator;

public class SelfExprNode extends PrimarioNode{

    private VarMetEncNode encadenado;

    public SelfExprNode(Token token, String struct, String metodo, VarMetEncNode encadenado) {
        super(token, metodo, struct);
        this.encadenado = encadenado;
    }

    @Override
    public String check(String structType, SymbolTable st) {
        return check(st);
    }

    @Override
    public String check(SymbolTable st) {
        if(encadenado != null){
            return encadenado.check(st);
        }else {
            return struct;
        }
    }

    @Override
    public void generateCode(CodeGenerator cg) {
        //TODO
    }
}
