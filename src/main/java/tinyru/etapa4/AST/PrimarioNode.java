package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

public abstract class PrimarioNode extends OperandoNode{
    protected Token token;
    protected String metodo;
    protected String struct;

    public PrimarioNode(Token token, String metodo, String struct){
        this.token = token;
        this.metodo = metodo;
        this.struct = struct;
    }

    public PrimarioNode(Token token, String metodo){
        this.token = token;
        this.metodo = metodo;
    }

    public PrimarioNode(Token token){
        this.token = token;
    }

}
