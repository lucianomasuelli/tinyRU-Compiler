package tinyru.etapa4.AST;

import tinyru.etapa1.Token;
import tinyru.etapa3.SymbolTable;

public abstract class PrimarioNode extends OperandoNode{
    protected Token token;

    public PrimarioNode(Token token){
        this.token = token;
    }



}
