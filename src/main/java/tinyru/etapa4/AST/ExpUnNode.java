package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class ExpUnNode extends ExpresionNode{
    ExpresionNode expRight;
    Token op;

    public ExpUnNode(){
    }

    public ExpUnNode(ExpresionNode expRight, Token op){
        this.expRight = expRight;
        this.op = op;
    }
}
