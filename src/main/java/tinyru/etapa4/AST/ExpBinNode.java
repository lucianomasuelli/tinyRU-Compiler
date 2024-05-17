package tinyru.etapa4.AST;

import tinyru.etapa1.Token;

public class ExpBinNode extends ExpresionNode {

    ExpresionNode expLeft;
    Token op;
    ExpresionNode expRight;

    public ExpBinNode(){
    }

    public ExpBinNode(ExpresionNode expLeft, Token op, ExpresionNode expRight){
        this.expLeft = expLeft;
        this.op = op;
        this.expRight = expRight;
    }

    public void print() {
        System.out.print("(");
        expLeft.print();
        System.out.print(" " + op.getLexeme() + " ");
        expRight.print();
        System.out.print(")");
    }
}
