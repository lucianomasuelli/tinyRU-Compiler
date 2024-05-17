package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public abstract class AccesoVarNode {
    Token id;

    public AccesoVarNode(Token id){
        this.id = id;
    }

    public void print() {
        System.out.print(id.getLexeme());
    }
}
