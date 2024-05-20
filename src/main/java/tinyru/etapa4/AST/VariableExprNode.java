package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

public class VariableExprNode extends PrimarioNode{
    private String metodo = null;
    private String struct = null;
    private PrimarioNode encadenado;
    private ExpresionNode arrayAccess = null;

    public VariableExprNode(Token token){
        super(token);
    }
    public VariableExprNode(Token token, String struct, String metodo){
        super(token);
        this.struct = struct;
        this.metodo = metodo;
    }

    public void setEncadenado(PrimarioNode encadenado){
        this.encadenado = encadenado;
    }

    public void setArrayAccess(ExpresionNode arrayAccess){
        this.arrayAccess = arrayAccess;
    }

    @Override
    public void print(){
        System.out.print(token.getLexeme());
    }

    @Override
    public String check(String structType, SymbolTable st){
        String type = null;
        if(encadenado != null){

        }
        else {

        }
        return type;
    }
}
